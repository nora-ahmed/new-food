import java.util.Scanner;
class CartItem {

    private Dish dish;
    private int quantity;

    public CartItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Dish getDish() {
        return this.dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.dish.getPrice();
    }

    public void customize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customize the dish: " + this.dish.getName());
        // Add your customization logic here
        // For example, ask the user for additional toppings, sides, etc.
    }

    public void display() {
        dish.display();
        System.out.println("Quantity: " + this.quantity);
    }
}