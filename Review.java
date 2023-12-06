import java.util.Scanner;

public  class Review {
    private User user;
//private Dish dish;
    private int rating;
    private String feedback;
    Review(User user){
        this.user=user;
    }
    public void getReview(/*User user,Dish dish*/){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter you rating form 1-5:");
        this.rating=  scanner.nextInt();
        System.out.println("Please enter you feedback:");
        this.feedback=scanner.next();
    }
    public String toString(){
        return user.getUserName()+","+rating+","+feedback;
    }

    public int getRating() {
        return rating;
    }

    public void display(){
        System.out.println(this.user.getUserName());

        System.out.println("Rating ="+rating);
        System.out.println("Feedback:"+feedback);

    }
}
