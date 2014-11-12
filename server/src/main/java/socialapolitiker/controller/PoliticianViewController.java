package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import socialapolitiker.model.domain.Politician;
import socialapolitiker.repository.PartyRepository;
import socialapolitiker.repository.PoliticianRepository;
import socialapolitiker.service.KeyValueCacheService;

@Controller
public class PoliticianViewController extends LayoutController {
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private KeyValueCacheService keyValueCacheService;

    @RequestMapping("/politiker/{twitterScreenName}")
    public String getPoliticianView(ModelMap modelMap, @PathVariable("twitterScreenName") String twitterScreenName)
            throws NoSuchRequestHandlingMethodException {

        Politician politician = politicianRepository.findOneByTwitterScreenName(twitterScreenName);

        if (politician == null) {
            throw new NoSuchRequestHandlingMethodException("getPoliticianView", PoliticianViewController.class);
        }

        modelMap.put("politician", politician);
        modelMap.put("party", politician.getParty());
        modelMap.put("wordViewData", keyValueCacheService.getWordViewData("politician.viewData." + twitterScreenName));

        return "politician";
    }
}
