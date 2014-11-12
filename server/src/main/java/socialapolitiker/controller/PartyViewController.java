package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import socialapolitiker.model.domain.Party;
import socialapolitiker.repository.PartyRepository;
import socialapolitiker.service.KeyValueCacheService;

@Controller
public class PartyViewController extends LayoutController {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private KeyValueCacheService keyValueCacheService;

    @RequestMapping("/parti/{partyUrlName}")
    public String getPartyView(ModelMap modelMap, @PathVariable("partyUrlName") String partyUrlName)
            throws NoSuchRequestHandlingMethodException {
        Party party = partyRepository.findOneByUrlName(partyUrlName);

        if (party == null) {
            throw new NoSuchRequestHandlingMethodException("getPartyView", PartyViewController.class);
        }

        modelMap.put("party", party);
        modelMap.put("wordViewData", keyValueCacheService.getWordViewData("party.viewData." + partyUrlName));

        return "party";
    }
}
