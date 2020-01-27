package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping(path = "/deimos")
    @ResponseBody
    public String home() {
        return "This is the landing page";
    }

    @GetMapping(path = "/home")
    public String welcome() {
        return "test";
    }
}
