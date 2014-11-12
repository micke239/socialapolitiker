package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialapolitiker.model.domain.Politician;
import socialapolitiker.repository.KeyValueCacheRepository;
import socialapolitiker.repository.PoliticianRepository;

@RestController
public class PoliticianController {

    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private KeyValueCacheRepository keyValueCacheRepository;

    @RequestMapping("/politician")
    public Politician getPolitician(String twitterScreenName) {
        return politicianRepository.findOneByTwitterScreenName(twitterScreenName);
    }

    @RequestMapping("/politicians")
    public String getPoliticians(String partyUrlName) {
        return keyValueCacheRepository.getOne("party.politicians." + partyUrlName).getValue();
    }

    @RequestMapping("/politician/view-data")
    public String getPopularWords(String twitterScreenName) {
        return keyValueCacheRepository.getOne("politician.viewData." + twitterScreenName).getValue();
    }

}
