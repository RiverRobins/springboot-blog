package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class controllerExample {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }
}