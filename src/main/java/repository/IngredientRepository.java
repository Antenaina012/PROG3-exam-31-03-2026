package repository;
import entity.Ingredient;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class IngredientRepository {
    private DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Ingredient> findAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ingredients.add(new Ingredient(rs.getInt("id"), rs.getString("name"), rs.getString("unit")));
            }
        }
        return ingredients;
    }
}