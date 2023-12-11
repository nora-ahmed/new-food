class CartItem {

    private Dish dish;
    protected int quantity;
    private final float CUSTOMIZATION_PRICE = 5.0f;
    private boolean[] customizations;  // New: Array of booleans to represent customizations

    public CartItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.customizations = new boolean[3];
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
    public boolean[] getCustomizations() {
        return customizations;
    }
    public void customize(int customizationIndex) {
        // Set the selected customization to true
        if (customizationIndex >= 0 && customizationIndex < customizations.length) {
            customizations[customizationIndex] = true;
            // Increase the total price for each customization
            //this.dish.setPrice(this.dish.getPrice() + CUSTOMIZATION_PRICE);
        }
    }
    public float getCustomizationPrice() {
        int numberOfCustomizations = 0;
        for (boolean customization : customizations) {
            if (customization) {
                numberOfCustomizations++;
            }
        }
        return numberOfCustomizations * CUSTOMIZATION_PRICE;
    }


}