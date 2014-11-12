package socialapolitiker.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import socialapolitiker.model.domain.Party;
import socialapolitiker.repository.PartyRepository;
import socialapolitiker.service.KeyValueCacheService;

public class LayoutController {
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private KeyValueCacheService keyValueCacheService;

    @ModelAttribute("parties")
    public List<Party> parties() {
        return partyRepository.findAll();
    }

    @ModelAttribute("cacheGenerated")
    public LocalDateTime cacheGenerated() {
        return keyValueCacheService.getCacheGenerated();
    }
}
