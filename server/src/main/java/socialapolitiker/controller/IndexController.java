package socialapolitiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import socialapolitiker.repository.PoliticianRepository;

@Controller
public class IndexController extends LayoutController {

    @Autowired
    private PoliticianRepository politicianRepository;

    @RequestMapping("/")
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("politicians", politicianRepository.findAll(new Sort(Direction.ASC, "name")));

        return "index";
    }
}
