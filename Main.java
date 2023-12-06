import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
class mainMenu {
    public void displayMainMenu() throws InterruptedException {

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

        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ") Restaurant: " + Main.r[i].getName());
            System.out.println("Rating: " + Main.r[i].getRatings());
        }

        System.out.println("Please enter your choice: 1-3:");

        int resChoice;

        while (true) {
            try {
                resChoice = resScanner.nextInt();
                if (resChoice >= 1 && resChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                resScanner.nextLine(); // Clear the invalid input
            }
        }

        Main.r[(resChoice - 1)].displayMenu();

        while (true) {
            System.out.println("Please enter your choice: 1-5 or enter 0 to go back:");

            int dishChoice;

            while (true) {
                try {
                    dishChoice = resScanner.nextInt();
                    if (dishChoice >= 0 && dishChoice <= 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    resScanner.nextLine(); // Clear the invalid input
                }
            }

            if (dishChoice == 0) {
                break;
            } else if (dishChoice >= 1 && dishChoice <= 5) {
                Dish d = Main.r[(resChoice - 1)].getDish(dishChoice - 1);
                CartItem ci = new CartItem(d, 1);
                Main.c.addItem(ci);
            }
        }
    }

    public void viewCart() throws InterruptedException {
        Main.c.display();
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
                    System.out.println("1) Customize your order.");
                    System.out.println("2) Edit the quantity.");
                    System.out.println("3) Delete an item.");
                    System.out.println("4) Go back");
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
                        break;

                    } else if (cusChoice == 2) {
                        break;
                    } else if (cusChoice == 3) {
                        System.out.println("Please enter the item's name you want to delete:");
                        String itemName;
                        Scanner scanner2 = new Scanner(System.in);
                        itemName = scanner2.next();
                        Main.c.removeItem(itemName);
                    } else if (cusChoice == 4) {
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
                    Main.thisUser = -1;
                    for (int i = 0; i < User.numberOfUser; i++) {
                        if (Main.user.get(i).getLogged()) {
                            Main.thisUser = i;
                            break;
                        }
                    }
                    Order order = new Order(Main.c, Main.user.get(Main.thisUser), Main.r[0], payment);
                    order.Preferred_DeliveryTime();
                    int orderTime = 2 * 60; // Convert minutes to seconds
                    OrderTimer orderTimer = new OrderTimer(orderTime);
                    orderTimer.startTimer();
                    order.DisplayOrder();
                    TimeUnit.MINUTES.sleep(2);
                    Review r = new Review(Main.user.get(Main.thisUser));
                    r.getReview();
                    r.display();
                    break;


                } else {
                    System.out.println("Payment failed. Please try again.");
                }
            } else if (cartChoice == 3) {
                break;
            }
        }
    }

    public void searchByCategory() {
        System.out.println("Please enter the category number:");

        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ") Category: " + Main.r[i].getCategory());
        }

        Scanner scanner = new Scanner(System.in);

        int categoryChoice;
        try {
            categoryChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); // Clear the invalid input
            return;
        }

        boolean flag = false;

        for (int i = 0; i < 3; i++) {
            if (categoryChoice == i + 1) {
                flag = true;
                Main.r[i].displayMenu();

                while (true) {
                    System.out.println("Please enter your choice: 1-5 or enter 0 to go back:");

                    int dishChoice;

                    try {
                        dishChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.nextLine(); // Clear the invalid input
                        continue;
                    }

                    if (dishChoice == 0) {
                        break;
                    } else if (dishChoice >= 1 && dishChoice <= 5) {
                        Dish d = Main.r[i].getDish(dishChoice - 1);
                        CartItem ci = new CartItem(d, 5);
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
                System.out.println("Please enter the restaurant name:");

                Scanner scanner1 = new Scanner(System.in);
                String resName = scanner1.next();
                boolean flag = false;

                for (int i = 0; i < 3; i++) {
                    if (resName.equals(Main.r[i].getName())) {
                        flag = true;
                        Main.r[i].displayMenu();

                        while (true) {
                            System.out.println("Please enter your choice: 1-5 or enter 0 to go back:");

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
                            } else if (dishChoice >= 1 && dishChoice <= 5) {
                                Dish d = Main.r[i].getDish(dishChoice - 1);
                                CartItem ci = new CartItem(d, 5);
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

    public void WelcomePage() throws InterruptedException /*,IOException*/ {

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

                    for (int i = 0; i < index; i++) {

                        if (username.equals(Main.user.get(i).getUserName()) && password.equals(Main.user.get(i).getPassword())) {

                            System.out.println("login successfully");
                            Main.user.get(i).setLogged();
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
//this.writeFiles();
                    break;
                }
                default: {
                    System.out.println("Invalid Option");

                    state = true;
                }
            }
        } while (state);
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
            System.out.println("5. Exit");
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
                    System.out.println("Exiting the program. Goodbye!");
                    exitRequested = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        }while (!exitRequested);
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
        BufferedWriter writer=new BufferedWriter(new FileWriter("users.txt"));
        for(int i=0;i<Main.user.size();i++){
            writer.write(Main.user.get(i).toString());
        }
        writer.close();
        Main.r[0].writeMenuFile("res1.txt");
        Main.r[0].writeReveiwFile("res1reviews.txt");
        Main.r[1].writeMenuFile("res2.txt");
        Main.r[1].writeReveiwFile("res2reviews.txt");
        Main.r[2].writeMenuFile("res3.txt");
        Main.r[2].writeReveiwFile("res3reviews.txt");
    }
}


class Main {

    static Restaurant r[] = new Restaurant[3];
    static User u = new User();
    static Menu m = new Menu();
    static Menu v = new Menu();
    static User farah = new User();
    static int thisUser;
    static List<User> user = new ArrayList<>();
    static Cart c = new Cart();

    public static void main(String[] args) throws InterruptedException {
        Dish d = new Dish("ldldl", 30, "dkdkk");
        m.addDish(d);
        for (int i = 1; i < 3; i++) {
            r[i] = new Restaurant("fkkfkf", "jhfhdfkj", "chineese", "fhjfkhkd", 4.5F, m);
        }


        r[0] = new Restaurant("Nora", "nora", "American", "nora", 3.5F, v);
        Dish b = new Dish("Pizza", 30, "sssss");
        v.addDish(b);
        for (int i = 1; i < 5; i++) {
            Dish m = new Dish("Burger", 50, "fries");
            v.addDish(m);
        }

        mainMenu j = new mainMenu();
        j.WelcomePage();


    }
}
