package repository;

import entity.Dish;
import entity.Ingredient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishRepository {

    public List<Dish> findAll() throws SQLException {
        Map<Integer, Dish> dishMap = new HashMap<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, sale_price FROM dish")) {
            while (rs.next()) {
                int dishId = rs.getInt("id");
                dishMap.put(dishId, new Dish(dishId, rs.getString("name"), rs.getDouble("sale_price"), new ArrayList<>()));
            }
        }

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT di.dish_id, i.id AS ingredient_id, i.name AS ingredient_name, i.unit AS ingredient_unit " +
                             "FROM dish_ingredient di " +
                             "JOIN ingredient i ON di.ingredient_id = i.id")) {
            while (rs.next()) {
                int dishId = rs.getInt("dish_id");
                Dish dish = dishMap.get(dishId);
                if (dish != null) {
                    dish.getIngredients().add(new Ingredient(rs.getInt("ingredient_id"), rs.getString("ingredient_name"), rs.getString("ingredient_unit")));
                }
            }
        }

        return new ArrayList<>(dishMap.values());
    }
}