import controller.DishController;
import controller.IngredientController;
import repository.DataSourceProvider;
import repository.DishRepository;
import repository.IngredientRepository;

public class Main {
    public static void main(String[] args) {
        try {
            DataSourceProvider.initializeDatabase();

            IngredientController ingredientController = new IngredientController(new IngredientRepository());
            DishController dishController = new DishController(new DishRepository());

            System.out.println("Ingredients:");
            ingredientController.getAllIngredients().forEach(System.out::println);

            System.out.println("\nDishes:");
            dishController.getAllDishes().forEach(d -> {
                System.out.println(d.getName() + " (" + d.getSalePrice() + "€)");
                d.getIngredients().forEach(i -> System.out.println("  - " + i.getName() + " (" + i.getUnit() + ")"));
            });

            System.out.println("\nApplication executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Échec de l'exécution : " + e.getMessage());
        }
    }
}
