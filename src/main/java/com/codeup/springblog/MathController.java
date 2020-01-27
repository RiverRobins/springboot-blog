package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping(path = "/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String sub(@PathVariable String num1, @PathVariable String num2) {
        return Integer.toString(Integer.parseInt(num2) - Integer.parseInt(num1));
    }

    @GetMapping(path = "/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable String num1, @PathVariable String num2) {
        return Integer.toString(Integer.parseInt(num1) + Integer.parseInt(num2));
    }

    @GetMapping(path = "/divide/{num1}/by/{num2}")
    @ResponseBody
    public String div(@PathVariable String num1, @PathVariable String num2) {
        return Integer.toString(Integer.parseInt(num1) / Integer.parseInt(num2));
    }

    @GetMapping(path = "/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String mult(@PathVariable String num1, @PathVariable String num2) {
        return Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));
    }
}
