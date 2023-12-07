import java.util.Scanner;

public  class Review {
    String name;
//private Dish dish;
    private int rating;
    private String feedback;

    Review(String name,int rating , String feedback){
this.name=name;
        this.rating = rating;
        this.feedback = feedback;
    }
    Review(String name){
        this.name=name;
    }
    public void getReview(/*User user,Dish dish*/){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter you rating form 1-5:");
        this.rating=  scanner.nextInt();
        System.out.println("Please enter you feedback:");
        this.feedback=scanner.next();
    }
    public String toString(){
        return name+","+rating+","+feedback;
    }

    public int getRating() {
        return rating;
    }

    public void display(){
        System.out.println(name);

        System.out.println("Rating ="+rating);
        System.out.println("Feedback:"+feedback);

    }
}
