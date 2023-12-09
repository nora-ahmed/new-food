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
        String response = scanner.next();

        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid choice. Please enter 'yes' or 'no':");
            response = scanner.next();
        }

        if (response.equalsIgnoreCase("yes")) {
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
            if (item.getDish().getName().equalsIgnoreCase(itemName)) {
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
        // Display the updated cart
        System.out.println(display());
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
    public float getTotalPrice() {
        float totalPrice = 0.0f;
        for (CartItem item : this.items) {
            totalPrice += (item.getDish().getPrice() + item.getCustomizationPrice()) * item.getQuantity();
        }
        return totalPrice;
    }



    public void emptyCart() {
        this.items.clear();
    }
    public void editQuantity(String itemName) {
        for (CartItem item : this.items) {
            if (item.getDish().getName().equalsIgnoreCase(itemName)) {
                System.out.println("Enter the new quantity for " + item.getDish().getName() + ": ");
                Scanner scanner = new Scanner(System.in);
                int newQuantity = scanner.nextInt();

                if (newQuantity == 0) {
                    // If user enters 0, remove the item from the cart
                    this.items.remove(item);
                    System.out.println("Item removed from the cart.");
                } else {
                    // Update the quantity
                    item.setQuantity(newQuantity);
                    System.out.println("Quantity updated successfully.");
                }
                // Display the updated cart
                System.out.println(display());
                return;
            }
        }

        // If the item is not found in the cart
        System.out.println("Item not found in cart.");
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
                        // Map customization index to actual customization name
                        String customizationName = mapIndexToCustomizationName(i);
                        cartDisplay.append("\n   - Customization: ").append(customizationName);
                    }
                }
            }

            cartDisplay.append("\n\nTotal Price: $").append(this.getTotalPrice());
        }

        return cartDisplay.toString();
    }

    // Helper method to map customization index to customization name
    private String mapIndexToCustomizationName(int index) {
        String[] customizationNames = {"extra potato", "salad", "water"};

        if (index >= 0 && index < customizationNames.length) {
            return customizationNames[index];
        } else {
            return "Unknown customization";
        }
    }



}
