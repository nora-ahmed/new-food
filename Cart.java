import java.util.ArrayList;
import java.util.List;


class Cart {

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < this.items.size(); i++) {
            CartItem item = this.items.get(i);
            if (item.getDish().getName().equals(itemName)) {
                this.items.remove(i);
                break;
            }
        }
    }
    public void removeLastItem() throws EmptyCartException {
        if (this.items.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        this.items.remove(this.items.size() - 1);
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : this.items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public void display() {
        System.out.println("Cart");
        for (CartItem item : this.items) {
            item.display();
        }
    }

    public static class EmptyCartException extends Exception {

        public EmptyCartException(String message) {
            super(message);
        }
    }
     public void emptyCart() {
        this.items.clear(); 
        System.out.println("Your cart is now empty");
     }

}
