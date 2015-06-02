package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import socialapolitiker.repository.PoliticianRepository;
import socialapolitiker.service.SocialapolitikerSearchService;

@Controller
public class IndexController extends LayoutController {

    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private SocialapolitikerSearchService socialapolitikerSearchService;

    @RequestMapping("/")
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("politicians", socialapolitikerSearchService.getPoliticians());

        return "index";
    }
}
