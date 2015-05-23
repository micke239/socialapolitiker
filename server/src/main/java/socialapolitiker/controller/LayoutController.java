package socialapolitiker.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import socialapolitiker.model.domain.Party;
import socialapolitiker.repository.PartyRepository;

public class LayoutController {
    @Autowired
    private PartyRepository partyRepository;

    @ModelAttribute("cacheGenerated")
    public LocalDateTime cacheGenerated() {
        return LocalDateTime.now();
    }

    @ModelAttribute("parties")
    public List<Party> parties() {
        return partyRepository.findAll();
    }
}
