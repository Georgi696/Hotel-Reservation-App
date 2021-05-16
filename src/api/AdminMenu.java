package api;

import services.CustomerService;
import services.ReservationService;

import java.util.Scanner;

public class AdminMenu {

    private static AdminMenu adminMenu;
    private AdminMenu() {}

    public static AdminMenu getInstance() {
        if (null == adminMenu) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }


    public static void start(){
        MainMenu mainMenu = new MainMenu();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin menu");
        System.out.println("-------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-------------------------");
        System.out.println("Please select menu number:");
        int select = Integer.parseInt(scanner.next());

        switch (select){
            case 1:
                System.out.println("All Customers");
                CustomerService.getInstance().getAllCustomers();
                mainMenu.start();
                break;
            case 2:
                System.out.println("All Rooms");
                mainMenu.start();

                break;
            case 3:
                System.out.println("All Reservations");
                ReservationService.getInstance().printAllReservetions();
                mainMenu.start();
                break;
            case 4:
                System.out.println("Enter a new Room number");
                scanner.nextInt();
                mainMenu.start();
                break;
            case 5:
                System.out.println("Exit to Main Menu? y/n");
                char gender = scanner.next().charAt(0);
                if (gender == 'y'){
                    mainMenu.start();
                }
                break;
        }
    }
}
