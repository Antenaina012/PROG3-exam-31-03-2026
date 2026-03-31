package repository;
import entity.Ingredient;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class IngredientRepository {
    public List<Ingredient> findAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";
        try (Connection conn = DataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ingredients.add(new Ingredient(rs.getInt("id"), rs.getString("name"), rs.getDouble("unit_price")));
            }
        }
        return ingredients;
    }
}