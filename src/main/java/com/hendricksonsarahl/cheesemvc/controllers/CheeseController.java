package com.hendricksonsarahl.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by Sarah Hendrickson @hendricksonsarahl
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // request path: /cheese/
    @RequestMapping(value = "")
    public String index(Model model) {

        ArrayList<String> cheeses = new ArrayList<>();
        cheeses.add("Kite Hill");
        cheeses.add("Follow Your Heart");
        cheeses.add("Gary");

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");


        return "cheese/index";
    }
}
