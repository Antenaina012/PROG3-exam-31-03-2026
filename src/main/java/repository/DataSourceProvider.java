package repository;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceProvider {
    private static final JdbcDataSource dataSource = new JdbcDataSource();

    static {
        dataSource.setURL("jdbc:h2:mem:td5;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = getDataSource().getConnection();
             Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS ingredient (id INT PRIMARY KEY, name VARCHAR(255), unit VARCHAR(50));");
            st.execute("CREATE TABLE IF NOT EXISTS dish (id INT PRIMARY KEY, name VARCHAR(255), sale_price DOUBLE);");
            st.execute("CREATE TABLE IF NOT EXISTS dish_ingredient (dish_id INT, ingredient_id INT, quantity DOUBLE, PRIMARY KEY (dish_id, ingredient_id));");

            st.execute("MERGE INTO ingredient KEY(id) VALUES (1, 'Tomato', 'pcs'), (2, 'Salt', 'g'), (3, 'Flour', 'g');");
            st.execute("MERGE INTO dish KEY(id) VALUES (1, 'Pizza', 12.50), (2, 'Salad', 8.50);");
            st.execute("MERGE INTO dish_ingredient KEY(dish_id, ingredient_id) VALUES (1, 1, 2.0), (1, 3, 100.0), (2, 1, 1.0), (2, 2, 5.0);");
        }
    }
}