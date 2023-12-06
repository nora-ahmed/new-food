import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Restaurant {
    //hyghytdhtrd
    private String name;
    private String address;
    private String category;
    private String contactInfo;
    private float ratings;
    private Menu menu;

    public void SetMenu(Menu menu){

        this.menu = menu;
    }

    public Restaurant(String name, String address, String category, String contactInfo, float ratings, Menu menu) {
        this.name = name;
        this.address = address;
        this.category=category;
        this.contactInfo = contactInfo;
        this.ratings = ratings;

    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void addDish(Dish dish) {
        menu.addDish(dish);
    }

    public void removeDish(Dish dish) {
        menu.deleteDish(dish);
    }

    public void updateContactInformation(String newContactInfo) {
        this.contactInfo = newContactInfo;
    }

    public double getRatings() {
        return ratings;
    }

    public void displayMenu() {
        System.out.println("Restaurant: " + name);
        System.out.println(" Rating: " + ratings);
        System.out.println("Menu:");
        menu.displayMenu();
    }

    public void SelectedDishNumber(int dishNumber) {
        System.out.println("Selected dish number: " + dishNumber);
    }
    public Dish getDish(int num){
        return menu.getDish(num);
    }
    public void writeMenuFile(String path) throws IOException {
        menu.writeFile(path);
    }
    /*public void writeReviewFile(String path) throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter(path));
        for(int i=0;i<reviews.size();i++){
            writer.write(reviews.get(i).toString());
        }
        writer.close();
    }*/
}