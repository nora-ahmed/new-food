import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Restaurant {
    private String name;
    private String address;
    private String category;
    private String contactInfo;
    private float ratings;
    private Menu menu;
    private List<Review> reviews;

    public Restaurant(String name, String address, String category, String contactInfo, float ratings, Menu menu)
    {
        this.name = name;

        this.address = address;

        this.category=category;

        this.contactInfo = contactInfo;

        this.ratings = ratings;

        this.menu = menu;

        this.reviews = new ArrayList<>();

    }
    public String getName() {

        return name;

    }

    public Menu getMenu()
    {

        return menu;

    }

    public String getCategory()
    {

        return category;

    }

    public void setCategory(String category)
    {

        this.category = category;

    }

    public String getAddress()
    {

        return address;

    }

    public String getContactInfo()
    {

        return contactInfo;

    }

    public void addDish(Dish dish)
    {

        menu.addDish(dish);

    }

    public void removeDish(Dish dish)
    {

        menu.deleteDish(dish);

    }

    public void updateContactInformation(String newContactInfo)
    {

        this.contactInfo = newContactInfo;

    }

    public double getRatings()
    {

        return ratings;

    }
    public void displayMenu()
    {

        System.out.println("Restaurant: " + name);

        System.out.println(" Rating: " + ratings);

        System.out.println("Menu:");

        menu.displayMenu();

    }
    public void SelectedDishNumber(int dishNumber)
    {

        System.out.println("Selected dish number: " + dishNumber);

    }
    public Dish getDish(int num)
    {

        return menu.getDish(num);

    }
    public void writeMenuFile(String path) throws IOException
    {

        menu.writeFile(path);

    }
    public float calcAverageRating() {

        if (reviews.isEmpty())
        {

            return ratings;

        }

        double sum = ratings;

        for (Review review : reviews)
        {

            sum += review.getRating();

        }

        return (float)sum / (float)(reviews.size() + 1);

    }
    public void addReview(Review review)
    {

        reviews.add(review);

        calcAverageRating();

    }
    public void displayReviews()
    {

        if (reviews.isEmpty())
        {

            System.out.println("No reviews available for this restaurant.");

        }
        else
        {
            System.out.println("Reviews for " + name + ":");

            for (Review review : reviews)
            {

                System.out.println(review);

            }
        }
    }
    /*public void writeReviewFile(String path) throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter(path));
        for(int i=0;i<reviews.size();i++){
            writer.write(reviews.get(i).toString());
        }
        writer.close();
    }*/
}