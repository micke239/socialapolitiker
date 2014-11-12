package socialapolitiker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import socialapolitiker.model.domain.Party;
import socialapolitiker.repository.KeyValueCacheRepository;
import socialapolitiker.repository.PartyRepository;

@RestController
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private KeyValueCacheRepository keyValueCacheRepository;

    @RequestMapping("/party")
    public Party getParty(@RequestParam(required = false) Long id, @RequestParam(required = false) String urlName) {
        if (id != null) {
            return partyRepository.findOne(id);
        }

        if (StringUtils.hasText(urlName)) {
            return partyRepository.findOneByUrlName(urlName);
        }

        return null;
    }

    @RequestMapping("/parties")
    public List<Party> getParties() {
        return partyRepository.findAll();
    }

    @RequestMapping("/party/view-data")
    public String getPopularWords(String urlName) {
        return keyValueCacheRepository.getOne("party.viewData." + urlName).getValue();
    }

}
