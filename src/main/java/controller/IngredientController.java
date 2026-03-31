package controller;

import entity.Ingredient;
import repository.IngredientRepository;

import java.sql.SQLException;
import java.util.List;

public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() throws SQLException {
        return ingredientRepository.findAll();
    }
}
