package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {


    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(a -> a.getDishId().equals(dishId)).findFirst().orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream().filter(a -> a.getDishId().equals(id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        if (dish.getDishId() == null) {
            DataHolder.dishes.add(dish);
        }

        DataHolder.dishes.remove(dish);
        DataHolder.dishes.add(dish);

        return dish;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(a -> a.getDishId().equals(id));
    }


}
