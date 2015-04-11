package socialapolitiker.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ModelAttribute;

public class LayoutController {
    @ModelAttribute("cacheGenerated")
    public LocalDateTime cacheGenerated() {
        return LocalDateTime.now();
    }
}
