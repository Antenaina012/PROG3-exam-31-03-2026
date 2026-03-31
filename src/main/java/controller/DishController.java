package controller;

import entity.Dish;
import repository.DishRepository;

import java.sql.SQLException;
import java.util.List;

public class DishController {
    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() throws SQLException {
        return dishRepository.findAll();
    }
}