import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class MainMenu {

    public static void ReadUser() throws IOException {
        //connect the program with the text file for reading

        File userFile = new File("user.txt");
        Scanner readFile = new Scanner(userFile);

        StringTokenizer token = null;

        String userName = "";
        String email = "";
        String password = "";
        String deliveryAddress = "";

        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");

            userName = token.nextToken();
            email = token.nextToken();
            password = token.nextToken();
            deliveryAddress = token.nextToken();

            User user = new User(userName, email, password, deliveryAddress);


            Main.user.add(user);
        }

    }

    public static void ReadDish1() throws IOException {
        //connect the program with the text file for reading
        File dishFile = new File("res1.txt");

        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;

        String name = "";
        float price = 0;
        String description = "";
        Menu menu=new Menu();
        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");

            name = token.nextToken();
            price = Float.parseFloat(token.nextToken());
            description = token.nextToken();

            Dish dish = new Dish(name, price, description);

            menu.addDish(dish);

        }

        Main.restaurants.get(0).setMenu(menu);

    }

    public static void ReadDish2() throws IOException {
        //connect the program with the text file for reading
        Menu menu=new Menu();

        File dishFile = new File("res2.txt");

        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;

        String name = "";
        float price = 0;
        String description = "";

        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");

            name = token.nextToken();
            price = Float.parseFloat(token.nextToken());
            description = token.nextToken();

            Dish dish = new Dish(name, price, description);

            menu.addDish(dish);

        }

        Main.restaurants.get(1).setMenu(menu);
    }

    public static void ReadDish3() throws IOException {

        Menu menu = new Menu();
        //connect the program with the text file for reading
        File dishFile = new File("res3.txt");
        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;

        String name = "";
        float price = 0;
        String description = "";

        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");

            name = token.nextToken();
            price = Float.parseFloat(token.nextToken());
            description = token.nextToken();

            Dish dish = new Dish(name, price, description);

            menu.addDish(dish);

        }

        Main.restaurants.get(2).setMenu(menu);
    }

    public static void ReadReview1() throws IOException {
        //connect the program with the text file for reading
        File dishFile = new File("review1.txt");
        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;
        String name="";
        int rating = 0;
        String feedback = "";

        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");
            name=token.nextToken();
            rating = Integer.parseInt(token.nextToken());
            feedback = token.nextToken();

            Review review = new Review(name,rating, feedback);

            Main.restaurants.get(0).addReview(review);

        }
    }

    public static void ReadReview2() throws IOException {
        //connect the program with the text file for reading
        File dishFile = new File("review2.txt");
        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;

        int rating = 0;
        String feedback = "";
        String name="";

        while (readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");
            name=token.nextToken();
            rating = Integer.parseInt(token.nextToken());
            feedback = token.nextToken();

            Review review = new Review(name,rating, feedback);

            Main.restaurants.get(1).addReview(review);

        }
    }


    //connect the program with the text file for reading
    public static void ReadReview3() throws IOException {
        File dishFile = new File("review3.txt");
        Scanner readFile = new Scanner(dishFile);

        StringTokenizer token = null;
        String name="";
        int rating = 0;
        String feedback = "";

        while(readFile.hasNextLine()) {

            token = new StringTokenizer(readFile.nextLine(), ",");
            name=token.nextToken();
            rating = Integer.parseInt(token.nextToken());
            feedback = token.nextToken();

            Review review = new Review(name,rating, feedback);

            Main.restaurants.get(2).addReview(review);

        }
    }

    public void  ReadAllFiles()throws IOException{

        ReadUser();
        ReadDish1();
        ReadDish2();
        ReadDish3();
        ReadReview1();
        ReadReview2();
        ReadReview3();

    }
    public void AdminDashboard()
    {
        Admin admin1 = new Admin();
        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        do {
            System.out.println("1. Add Dish to Menu");
            System.out.println("2. Remove Dish from Menu");
            System.out.println("3. Update Dish Price");
            System.out.println("4. View Menu");
            System.out.println("5. View Daily Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter dish name: ");
                    String dishName = scanner.nextLine();
                    System.out.println("Enter dish price: ");
                    float dishPrice = getValidFloatInput(scanner);
                    System.out.print("Enter dish description: ");
                    String dishDescription = scanner.nextLine();
                    Dish newDish = new Dish(dishName, dishPrice, dishDescription);
                    admin1.addDishToMenu(newDish);
                    break;

                case 2:
                    System.out.print("Enter dish name to remove: ");
                    String dishToRemove = scanner.nextLine();
                    Dish dish = new Dish(dishToRemove);
                    admin1.removeDishFromMenu(dish);
                    break;

                case 3:
                    System.out.print("Enter dish name to update price: ");
                    String dishToUpdate = scanner.nextLine();
                    Dish dishToUpdateObj = new Dish(dishToUpdate);
                    System.out.print("Enter new dish price: ");
                    float newPrice = getValidFloatInput(scanner);
                    admin1.updateDishPrice(dishToUpdateObj, newPrice);
                    break;

                case 4:
                    admin1.viewMenu();
                    break;
                case 5:
                    admin1.generateReports();
                    break;

                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    exitRequested = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        }while (!exitRequested);
    }

    public void WelcomePage() throws InterruptedException ,IOException {

        Scanner read = new Scanner(System.in);


        boolean state = false;
        do {

            int option=0;

            boolean checkRegistration = false;

            boolean checkLogin = false;

            int index = User.numberOfUser;

            System.out.println("Welcome To Our Program");

            System.out.println("press 1 to register");

            System.out.println("press 2 to login");

            System.out.println("press 0 to close the program");
            try {
                option = read.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                read.nextLine(); // Clear the invalid input
                WelcomePage();
            }


            switch (option) {
                case 1: {

                    User u = new User();

                    System.out.println("Enter your username");
                    String username = read.next();

                    for (int i = 0; i < index; i++) {

                        if (username.equals(Main.user.get(i).getUserName())) {
                            System.out.println("username already exists");

                            checkRegistration = true;

                            break;
                        }

                    }

                    if (checkRegistration) {

                        state = true;

                        break;

                    }

                    System.out.println("Enter your Email");
                    String email = read.next();

                    for (int i = 0; i < index; i++) {

                        if (email.equals(Main.user.get(i).getEmail())) {
                            System.out.println("email already exists");

                            checkRegistration = true;

                            break;
                        }

                    }

                    if (checkRegistration) {

                        state = true;

                        break;

                    }

                    u.setUserName(username);
                    u.setEmail(email);
                    read.nextLine();

                    System.out.println("Enter your Delivery Address");
                    String deliveryAddress = read.nextLine();
                    u.setDeliveryAddress(deliveryAddress);


                    System.out.println("Enter your Password");
                    String password = read.next();
                    u.setPassWord(password);
                    Main.user.add(u);



                    User.numberOfUser++;

                    state = true;

                    break;
                }
                case 2: {
                    System.out.println("Enter your User Name");
                    String username = read.next();

                    System.out.println("Enter your Password");
                    String password = read.next();
                    if(username.equals("nora")&&password.equals("12")){
                        this.AdminDashboard();
                        break;
                    }

                    for (int i = 0; i < Main.user.size(); i++) {

                        if (username.equals(Main.user.get(i).getUserName()) && password.equals(Main.user.get(i).getPassword())) {

                            System.out.println("login successfully");
                            Main.thisUser=i;
                            checkLogin = true;
                            this.displayMainMenu();


                        }
                    }

                    //checkLogin = true;

                    if (!checkLogin) {
                        System.out.println("invalid userName or password");
                    }

                    state = true;


                    break;
                }
                case 0: {
                    state = false;

                    this.writeFiles();
                    break;
                }
                default: {
                    System.out.println("Invalid Option");

                    state = true;
                }
            }
        } while (state);
    }

    public void displayMainMenu() throws InterruptedException {
// hello
        while (true) {
            int choice;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your choice:");
            System.out.println("1) Display all restaurants.");
            System.out.println("2) Search.");
            System.out.println("3) View cart.");
            System.out.println("4) Log out.");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    displayRestaurants();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    this.viewCart();
                    // Display cart
                    // Custom
                    // Edit: quantity delete
                    // Place order
                    break;
                case 4:
                    Main.thisUser = -1;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayRestaurants() {
        Scanner resScanner = new Scanner(System.in);

        for (int i = 0; i < Main.restaurants.size(); i++) {
            System.out.println((i + 1) + ") Restaurant: " + Main.restaurants.get(i).getName());
            System.out.println("Rating: " + Main.restaurants.get(i).getRatings());
        }

System.out.println("0) Go back");
        System.out.println("Please enter your choice: 0-3:");

        int resChoice;

        while (true) {
            try {
                resChoice = resScanner.nextInt();
                if (resChoice==0){
                   return;
                }
                else if (resChoice >= 1 && resChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                resScanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("1) View Menu");
        System.out.println("2) View Reviews");
        System.out.println("3) Go back");
        System.out.println("Please enter your choice: 1-3:");
        int viewChoice=0;

        while(true){
            try{
                viewChoice=resScanner.nextInt();
                if(viewChoice==3){
                  return;
                }
                else if(viewChoice==1){
                    // Main.restaurants.get(resChoice - 1).displayMenu();
                    break;
                }
                else if(viewChoice==2){   Main.restaurants.get(resChoice - 1).displayReviews();
                    break;
                }
                else
                {
                    System.out.println("Invalid choice. Please enter either 1 or 3.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                resScanner.nextLine(); // Clear the invalid input
            }


        }

        Main.restaurants.get((resChoice - 1)).displayMenu();

        while (true) {
            System.out.println("Please enter your choice: 1-"+Main.restaurants.get(resChoice-1).getMenuSize()+" or 0 to go back:");

            int dishChoice;


            while (true) {
                try {
                    dishChoice = resScanner.nextInt();
                    if(dishChoice==0){
                      return;
                    }
                    if (dishChoice > 0 && dishChoice <= Main.restaurants.get(resChoice-1).getMenuSize()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and "+Main.restaurants.get(resChoice-1).getMenuSize()+":");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    resScanner.nextLine(); // Clear the invalid input
                }
            }


            if (dishChoice == 3) {
                break;
            } else if (dishChoice >= 1 && dishChoice <= Main.restaurants.size()) {
                Main.thisRes=resChoice-1;
                Dish d = Main.restaurants.get((resChoice - 1)).getDish(dishChoice - 1);
                CartItem ci = new CartItem(d, 1);
                Main.c.addItem(ci);
            }
        }
    }



    public void searchByCategory() {
        System.out.println("Please enter the category number:");

        for (int i = 0; i < Main.restaurants.size(); i++) {
            System.out.println((i + 1) + ") Category: " + Main.restaurants.get(i).getCategory());
        }
System.out.println("0) Go back");
        Scanner scanner = new Scanner(System.in);

        int categoryChoice;
        try {
            categoryChoice = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); // Clear the invalid input
            return;
        }
if (categoryChoice==0){
    return;
}
        boolean flag = false;


        for (int i = 0; i < Main.restaurants.size(); i++) {
            if (categoryChoice == i + 1) {
                flag = true;
                Main.restaurants.get(i).displayMenu();

                while (true) {
                    System.out.println("Please enter your choice: 1-"+Main.restaurants.get(i).getMenuSize()+" or enter 0 to go back:");

                    int dishChoice;

                    try {
                        dishChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }

                    if (dishChoice == 0) {
                       return;
                    } else if (dishChoice >= 1 && dishChoice <= Main.restaurants.size()) {
                        Main.thisRes=i;
                        Dish d = Main.restaurants.get(i).getDish(dishChoice - 1);
                        CartItem ci = new CartItem(d, 1);
                        Main.c.addItem(ci);
                    }
                }
            }
        }

        if (!flag) {
            System.out.println("Invalid category choice. Please try again.");
        }
    }

    public void search() {
        while (true) {
            System.out.println("Please enter your choice:");
            System.out.println("1) Search by restaurant name.");
            System.out.println("2) Search by restaurant category.");
            System.out.println("3) Go back.");

            Scanner scanner = new Scanner(System.in);

            int searchChoice;

            try {
                searchChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            if (searchChoice == 1) {
                System.out.println("Please enter the restaurant name or 0 to go back:");

                Scanner scanner1 = new Scanner(System.in);
                String resName = scanner1.next();
                if(resName.equals("0")){
                 return;
                }
                boolean flag = false;

                for (int i = 0; i < Main.restaurants.size(); i++) {
                    if (resName.equals(Main.restaurants.get(i).getName())) {
                        flag = true;
                        Main.restaurants.get(i).displayMenu();

                        while (true) {
                            System.out.println("Please enter your choice: 1-"+Main.restaurants.get(i).getMenuSize()+" or enter 0 to go back:");

                            Scanner scanner2 = new Scanner(System.in);

                            int dishChoice;

                            try {
                                dishChoice = scanner2.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner2.nextLine(); // Clear the invalid input
                                continue;
                            }

                            if (dishChoice == 0) {
                                break;
                            } else if (dishChoice >= 1 && dishChoice <=Main.restaurants.size()) {
                                Main.thisRes=i;
                                Dish d = Main.restaurants.get(i).getDish(dishChoice - 1);
                                CartItem ci = new CartItem(d, 1);
                                Main.c.addItem(ci);
                            }
                        }
                    }
                }

                if (!flag) {
                    System.out.println("Sorry, we don't have this restaurant.");
                    break;
                }
            } else if (searchChoice == 2) {
                searchByCategory();
            } else if (searchChoice == 3) {
                break;
            }
        }
    }

    public void viewCart() throws InterruptedException {

        String cartDisplay = Main.c.display(); // Get the cart display string
        // Print the cart display
        System.out.println(cartDisplay);
        if (cartDisplay.equals("Your Cart is Empty.")){
            return;
        }


        while (true) {
            System.out.println("Please enter your choice:");
            System.out.println("1) Edit your order.");
            System.out.println("2) Place your order.");
            System.out.println("3) Go back.");
            Scanner scanner = new Scanner(System.in);
            int cartChoice;
            try {
                cartChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Continue the loop
            }

            if (cartChoice == 1) {
                while (true) {
                    System.out.println("Please enter your choice:");
                    System.out.println("1) Edit quantity.");
                    System.out.println("2) Delete an item.");
                    System.out.println("3) Go back");
                    Scanner scanner1 = new Scanner(System.in);
                    int cusChoice;

                    try {
                        cusChoice = scanner1.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner1.nextLine(); // Clear the invalid input
                        continue;
                    }

                    if (cusChoice == 1) {
                        // Edit quantity
                        System.out.println("Please enter the item's name you want to edit quantity for or 0 to go back:");
                        String itemName;
                        Scanner scanner2 = new Scanner(System.in);
                        itemName = scanner2.nextLine();
                        if(itemName.equals("0")){
                            break;
                        }
                        Main.c.editQuantity(itemName);

                    }  else if (cusChoice == 2) {
                        System.out.println("Please enter the item's name you want to delete or 0 to go back:");
                        String itemName;
                        Scanner scanner2 = new Scanner(System.in);
                        itemName = scanner2.nextLine();
                        if(itemName.equals("0")){
                            break;
                        }
                        Main.c.removeItem(itemName);

                    } else if (cusChoice == 3) {
                        break;
                    }

                }
            } else if (cartChoice == 2) {
                // Process payment
                Payment payment = new Payment(Main.c.getTotalPrice());
                payment.processPayment();

                // Check if the payment was successful
                if (payment.getPaymentStatus()) {
                    // Create the order


                    Order order = new Order(Main.c, Main.user.get(Main.thisUser), Main.restaurants.get(Main.thisRes), payment);
                    order.Preferred_DeliveryTime();
                    int orderTime = 2 * 60; // Convert minutes to seconds
                    OrderTimer orderTimer = new OrderTimer(orderTime);
                    orderTimer.startTimer();
                    order.DisplayOrder();
                    TimeUnit.MINUTES.sleep(2);
                    Main.c.emptyCart();
                    Review userReview = new Review(Main.user.get(Main.thisUser).getUserName());
                    userReview.getReview();
                    Main.restaurants.get(Main.thisRes).addReview(userReview);

                    break;


                } else {
                    System.out.println("Payment failed. Please try again.");
                }
            } else if (cartChoice == 3) {
                break;
            }
        }
    }

    private static float getValidFloatInput(Scanner scanner) {
        float value;
        while (true) {
            try {
                value = Float.parseFloat(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid float.");
            }
        }
        return value;
    }

    private static int getValidIntegerInput(Scanner scanner) {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        return value;
    }
    public void writeFiles() throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter("user.txt"));
        for(int i=0;i<Main.user.size();i++){
            writer.write(Main.user.get(i).toString());
            writer.newLine();
        }
        writer.close();
        Main.restaurants.get(0).writeMenuFile("res1.txt");
        Main.restaurants.get(0).writeReviewFile("review1.txt");
        Main.restaurants.get(1).writeMenuFile("res2.txt");
        Main.restaurants.get(1).writeReviewFile("review2.txt");
        Main.restaurants.get(2).writeMenuFile("res3.txt");
        Main.restaurants.get(2).writeReviewFile("review3.txt");
    }

}
