package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishForm(Model model){
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            model.addAttribute("chefs", chefService.listChefs());
            return "dish-form";
        } catch (RuntimeException e) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime, @RequestParam Long chefId) {

        Chef chef = chefService.findById(chefId);
        dishService.create(dishId, name, cuisine, preparationTime, chef);

        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime, @RequestParam Long chefId) {


        Chef chef = chefService.findById(chefId);
        dishService.update(id, dishId, name, cuisine, preparationTime, chef);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/add-to-chef")
    public String showAddDishToChefPage(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("dishes", dishService.listDishes());
        return "addDishToChef";
    }

    @PostMapping("/add-to-chef")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId) {

        chefService.addDishToChef(chefId, dishId);
        return "redirect:/chefs/" + chefId;
    }
}
