package socialapolitiker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import socialapolitiker.model.domain.Party;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedEntity;
import socialapolitiker.repository.PartyRepository;
import socialapolitiker.service.SocialapolitikerSearchService;

@Controller
public class PartyViewController extends LayoutController {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private SocialapolitikerSearchService socialapolitikerSearchService;

    @RequestMapping("/parti/{partyUrlName}")
    public String getPartyView(ModelMap modelMap, @PathVariable("partyUrlName") String partyUrlName)
            throws NoSuchRequestHandlingMethodException {
        Party party = partyRepository.findOneByUrlName(partyUrlName);

        if (party == null) {
            throw new NoSuchRequestHandlingMethodException("getPartyView", PartyViewController.class);
        }

        List<TweetedEntity> tweetedWords = socialapolitikerSearchService.getTweetedWordsByParty(partyUrlName);
        List<TweetedEntity> tweetedDomains = socialapolitikerSearchService.getTweetedDomainsByParty(partyUrlName);
        List<TweetedEntity> tweetedUserMentions = socialapolitikerSearchService
                .getTweetedUserMentionsByParty(partyUrlName);
        List<TweetedEntity> tweetedHashtags = socialapolitikerSearchService.getTweetedHashtagsByParty(partyUrlName);
        List<PopularWord> popularWords = socialapolitikerSearchService.getPopularWordsByParty(partyUrlName);

        party.getPoliticians().sort((p1, p2) -> {
            return p1.getName().compareTo(p2.getName());
        });

        modelMap.put("party", party);
        modelMap.put("politicians", socialapolitikerSearchService.getPoliticians(partyUrlName));
        modelMap.put("tweetedWords", tweetedWords);
        modelMap.put("popularWords", popularWords);
        modelMap.put("tweetedDomains", tweetedDomains);
        modelMap.put("tweetedUserMentions", tweetedUserMentions);
        modelMap.put("tweetedHashtags", tweetedHashtags);

        return "party";
    }
}
