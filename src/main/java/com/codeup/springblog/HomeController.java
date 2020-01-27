package com.codeup.springblog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HomeController {
    @RequestMapping(path = "/")
    @ResponseBody
    public String addOne() {
        return "This is the landing page";
    }
}
