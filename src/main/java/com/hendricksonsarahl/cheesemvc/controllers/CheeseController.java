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
        model.addAttribute(new Cheese()); // passing this basic "skeleton" on a cheese object, can use the properties of the object to render the form
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    // @ModelAttribute: model binding, Spring Boot framework creates object for us when it's passed through the handler method
    // @Valid: when framework is doing this process, it needs to check that it's valid according to what is defined in the Cheese model
    // @Errors: takes the result of @Valid and if not valid, provides error messages to show in the view
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, Model model) {
// if we find errors, go back and render the cheese/add form again (do not create new cheese object)
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
        model.addAttribute("title", "Edit Cheese " + editCheese.getName() + " ID " + cheeseId );
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";

    }
    
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese editCheese, Errors errors, @RequestParam String name, String description, CheeseType type, int rating) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Edit Cheese " + editCheese.getName());
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }


        return "redirect:/cheese";
    }
}