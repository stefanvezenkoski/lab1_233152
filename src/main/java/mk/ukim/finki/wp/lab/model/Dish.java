package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @ManyToOne
    private Chef chef;

    public Dish(){}

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Long getId() {
        return id;
    }

    public String getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public Chef getChef() {
        return chef;
    }
}
