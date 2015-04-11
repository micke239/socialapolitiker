package socialapolitiker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import socialapolitiker.model.domain.Politician;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;
import socialapolitiker.repository.PartyRepository;
import socialapolitiker.repository.PoliticianRepository;
import socialapolitiker.service.SocialapolitikerSearchService;

@Controller
public class PoliticianViewController extends LayoutController {
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private SocialapolitikerSearchService socialapolitikerSearchService;

    @RequestMapping("/politiker/{twitterScreenName}")
    public String getPoliticianView(ModelMap modelMap, @PathVariable("twitterScreenName") String twitterScreenName)
            throws NoSuchRequestHandlingMethodException {

        Politician politician = politicianRepository.findOneByTwitterScreenName(twitterScreenName);

        if (politician == null) {
            throw new NoSuchRequestHandlingMethodException("getPoliticianView", PoliticianViewController.class);
        }

        List<TweetedWord> tweetedWords = socialapolitikerSearchService.getTweetedWordsByPolitician(twitterScreenName);
        List<PopularWord> popularWords = socialapolitikerSearchService.getPopularWordsByPolitician(twitterScreenName);

        politician.getParty().getPoliticians().sort((p1, p2) -> {
            return p1.getName().compareTo(p2.getName());
        });

        modelMap.put("politician", politician);
        modelMap.put("party", politician.getParty());
        modelMap.put("tweetedWords", tweetedWords);
        modelMap.put("popularWords", popularWords);

        return "politician";
    }
}
