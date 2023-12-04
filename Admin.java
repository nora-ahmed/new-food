import java.util.List;

public class Admin extends Person {
    private Menu menu;
    private List<Order> orders;

    public Admin() {
        this.menu = new Menu();
    }

    public void addDishToMenu(Dish dish) {
        menu.addDish(dish);
    }

    public void removeDishFromMenu(Dish dish) {
        menu.deleteDish(dish);
    }

    public void updateDishPrice(Dish dish, float newPrice) {
        menu.getDishes().stream()
                .filter(d -> d.getName().equalsIgnoreCase(dish.getName()))
                .findFirst()
                .ifPresent(d -> {
                    d.setPrice(newPrice);
                    System.out.println("Dish price updated successfully.");
                });
    }
    public void viewMenu() {
        System.out.println("Menu:");
        menu.displayMenu();
    }
}
