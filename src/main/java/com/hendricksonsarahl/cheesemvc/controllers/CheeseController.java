package com.hendricksonsarahl.cheesemvc.controllers;

import com.hendricksonsarahl.cheesemvc.models.Cheese;
import com.hendricksonsarahl.cheesemvc.models.CheeseData;
import com.hendricksonsarahl.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese editCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", editCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";

    }
    
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese editCheese, Errors errors,  @RequestParam String name, String description, CheeseType type) {

        if (errors.hasErrors()){
            model.addAttribute("title", "edit cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        int prevId = editCheese.getCheeseId();
        CheeseData.remove(prevId);
        editCheese.setName(name);
        editCheese.setDescription(description);
        editCheese.setType(type);
        model.addAttribute("cheese", editCheese);
        CheeseData.add(editCheese);

        return "redirect:/cheese";
    }
}