package velocity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestAction {

    @RequestMapping("/index.htm")
    public String index() {
        return "index";
    }
}
