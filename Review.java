import java.util.InputMismatchException;
import java.util.Scanner;

public  class Review {
    String name;

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
    public void getReview(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter you rating form 1-5:");
        int ratings;
        while (true) {
            try {



                ratings=   scanner.nextInt();

                if(ratings>=1&&ratings<=5){
                    this.rating=ratings;
                    break;
                }
               else{
                   System.out.println("Invalid, please enter a number between 1-5:");

                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }

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
