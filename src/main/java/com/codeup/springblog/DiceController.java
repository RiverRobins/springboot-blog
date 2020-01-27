package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.callback.PrivateKeyCallback;
import java.util.ArrayList;

@Controller
public class DiceController {
    @GetMapping(path = "/dice")
    public String home() {
        return "dice";
    }

    @PostMapping(path = "/dice")
    public String dice(@RequestParam(name = "guess") String guess, @RequestParam(name = "rolls") String rolls, Model model) {
        model.addAttribute("roll-amount", rolls);
        model.addAttribute("guess", guess);
        ArrayList<String> dice = new ArrayList<>();
        int right = 0;
        for (int i = 0; i < Integer.parseInt(rolls); i++) {
            dice.add(diceRoll());
            if (dice.get(i).equalsIgnoreCase(guess)){
                right++;
            }
        }
        model.addAttribute("rolls", dice);
        model.addAttribute("right", right);
        return "dice";
    }

    private String diceRoll(){
        return Integer.toString((int) Math.floor(Math.random() * 6));
    }
}
