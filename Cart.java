import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cart {

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        // Ask the user if they want to customize the item
        System.out.println("Do you want to customize '" + item.getDish().getName() + "'? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        while (!response.equals("yes") && !response.equals("no")) {
            System.out.println("Invalid choice. Please enter 'yes' or 'no':");
            response = scanner.nextLine().toLowerCase();
        }

        if (response.equals("yes")) {
            System.out.println("Available customizations: 1. extra potato, 2. salad, 3. water");
            System.out.println("Choose a customization (enter the corresponding number):");
            int customizationIndex = scanner.nextInt() - 1;  // Adjust for array index

            if (customizationIndex >= 0 && customizationIndex < item.getCustomizations().length) {
                item.customize(customizationIndex);
            } else {
                System.out.println("Invalid customization choice.");
            }
        }

        this.items.add(item);
    }



    public void removeItem(String itemName) {
        int itemIndex = -1;
        for (int i = 0; i < this.items.size(); i++) {
            CartItem item = this.items.get(i);
            if (item.getDish().getName().equals(itemName)) {
                itemIndex = i;
                break;
            }
        }

        if (itemIndex == -1) {
            System.out.println("Item not found in cart.");
            return;
        }

        CartItem item = this.items.get(itemIndex);
        if (item.quantity > 1) {
            System.out.println("Enter the quantity you want to remove for " + item.getDish().getName() + ": ");
            Scanner scanner = new Scanner(System.in);
            int quantityToRemove = scanner.nextInt();

            if (quantityToRemove > item.quantity) {
                System.out.println("Invalid quantity. Please enter a quantity less than or equal to the item's quantity.");
                return;
            }

            item.quantity -= quantityToRemove;
            if (item.quantity == 0) {
                this.items.remove(itemIndex);
            }
        } else {
            this.items.remove(itemIndex);
        }

    }


    public static class EmptyCartException extends Exception {

        public EmptyCartException(String message) {
            super(message);
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
           // totalPrice += item.getPrice() * item.quantity;
        }
        return totalPrice;
    }
    public void emptyCart() {
        this.items.clear();
        System.out.println("Your cart is now empty");
    }
    public String display() {
        StringBuilder cartDisplay = new StringBuilder();

        if (this.items.isEmpty()) {
            cartDisplay.append("Your Cart is Empty.");
        } else {
            cartDisplay.append("Your Cart:\n");
            int counter = 1;

            for (CartItem item : this.items) {
                cartDisplay.append("\n")
                        .append(counter++).append("- ")
                        .append(item.getDish().getName())
                        .append(" (Quantity: ")
                        .append(item.getQuantity())
                        .append(")");

                // Display customizations if any
                boolean[] customizations = item.getCustomizations();
                for (int i = 0; i < customizations.length; i++) {
                    if (customizations[i]) {
                        cartDisplay.append("\n   - Customization ").append(i + 1);
                    }
                }
            }

            cartDisplay.append("\n\nTotal Price: $").append(this.getTotalPrice());
        }

        return cartDisplay.toString();
    }


}
