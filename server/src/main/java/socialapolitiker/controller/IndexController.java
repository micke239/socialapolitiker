package socialapolitiker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends LayoutController {
    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }
}
