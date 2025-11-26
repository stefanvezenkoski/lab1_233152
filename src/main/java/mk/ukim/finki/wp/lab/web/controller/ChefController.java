package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping
    public String listChefs(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        return "chefsList";
    }

    @GetMapping("/{id}")
    public String showChefDetails(@PathVariable Long id, Model model) {
        var chef = chefService.findById(id);
        if (chef == null) {
            return "redirect:/chefs";
        }
        model.addAttribute("chef", chef);
        return "chefDetails";
    }
}
