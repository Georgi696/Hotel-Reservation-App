package api;

import java.util.Scanner;

public class MainMenu {
    static AdminMenu adminMenu = AdminMenu.getInstance();
    private static HotelResource hotelResource = HotelResource.getInstance();

        public static void start(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Hotel Reservation App");
            System.out.println("-------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservation");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("-------------------------");
            System.out.println("Please select menu number:");
            int select = scanner.nextInt();
            String pattern2 = "[0-9]*";
            String pattern1 = "[A-Za-z]*";

                switch (select) {
                    case 1:
                        System.out.println("Enter a room number");
                        while (!scanner.hasNext(pattern2)) {
                            System.out.println("Numeric only");
                            scanner.next();
                        }
                        System.out.println(scanner.next());
                        break;
                    case 2:
                        System.out.println("Enter a reservation number");
                        //scanner.nextInt();
                        while (!scanner.hasNext(pattern2)) {
                            System.out.println("Numeric only");
                            scanner.next();
                        }
                        System.out.println(scanner.next());
                        break;
                    case 3:
                        createAccount();
                        break;
                    case 4:
                        adminMenu.start();
                        break;
                    case 5:
                        System.out.println("Exit? y/n");
                        char gender = scanner.next().charAt(0);
                        if (gender == 'y') {
                            System.out.println("Exit");
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
            }
        }

        public static void createAccount(){
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------");
            System.out.println("Enter email address");
            String emailInput = sc.next();
            System.out.println("Enter your First Name");
            String firstName = sc.next();
            System.out.println("Enter your Last Name");
            String lastName = sc.next();
            hotelResource.createACustomer(firstName,lastName,emailInput);
            start();
        }
    }
