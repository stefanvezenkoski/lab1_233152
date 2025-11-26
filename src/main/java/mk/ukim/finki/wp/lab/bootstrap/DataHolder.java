package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){
        dishes.add(new Dish("1", "Pasta Carbonara", "Italian", 25));
        dishes.add(new Dish("2", "Sushi Roll", "Japanese", 40));
        dishes.add(new Dish("3", "Tacos", "Mexican", 20));
        dishes.add(new Dish("4", "Moussaka", "Greek", 50));
        dishes.add(new Dish("5", "Burger", "American", 15));
        dishes.add(new Dish("6", "Tavce grafce", "Macedonian", 15));
        dishes.add(new Dish("7", "Trilece", "Macedonian", 15));
        dishes.add(new Dish("8", "Shopska salad", "Macedonian", 15));

        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World famous chef",
                new ArrayList<>(dishes.subList(0, 2))));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "Healthy food lover",
                new ArrayList<>(dishes.subList(2, 3))));
        chefs.add(new Chef(3L, "Nigella", "Lawson", "British home cooking queen",
                new ArrayList<>(dishes.subList(3, 4))));
        chefs.add(new Chef(4L, "Massimo", "Bottura", "Italian fine dining legend",
                new ArrayList<>(dishes.subList(1, 3))));
        chefs.add(new Chef(5L, "Thomas", "Keller", "American chef, perfectionist",
                new ArrayList<>(dishes.subList(4, 5))));
        chefs.add(new Chef(6L, "Stefan", "Stefanovski", "Macedonian chef, the best of best",
                new ArrayList<>(dishes.subList(6, 7))));
    }
}
