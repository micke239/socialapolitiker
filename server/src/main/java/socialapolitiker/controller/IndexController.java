package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import socialapolitiker.repository.PartyRepository;
import socialapolitiker.repository.PoliticianRepository;

@Controller
public class IndexController extends LayoutController {
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PoliticianRepository politicianRepository;

    @RequestMapping("/")
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("parties", partyRepository.findAll());
        modelMap.addAttribute("politicians", politicianRepository.findAll());

        return "index";
    }
}
