import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu {
    private ArrayList<Dish> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<Dish>();
    }

    public void addDish(Dish menuItem) {
        menuItems.add(menuItem);
    }


    public void deleteDish(Dish dish) {
        Iterator<Dish> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            Dish currentDish = iterator.next();

            if (currentDish.getName().equalsIgnoreCase(dish.getName())) {
                iterator.remove();
                System.out.println("The dish " + dish.getName() + " was deleted from the menu.");
                return;
            }
        }
        System.out.println("The dish " + dish.getName() + " was not found in the menu.");
    }
    public void displayMenu() {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.print((i + 1) + ". ");
            menuItems.get(i).display();
        }
    }

    public Dish getDish(int num) {
        return menuItems.get(num);
    }

    public List<Dish> getDishes() {
        return menuItems;
    }
}