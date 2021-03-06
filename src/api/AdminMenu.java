package api;

import model.IRoom;
import model.Room;
import model.RoomType;
import services.CustomerService;
import services.ReservationService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu{
    public static AdminResource adminResource = AdminResource.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();
    private static final MainMenu mainMenu = MainMenu.getInstance();
    private static AdminMenu adminMenu;
    private AdminMenu() {}

    public static AdminMenu getInstance() {
        if (null == adminMenu) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }


    public static void start() throws ParseException {
        int select = adminMenu();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            do {
                switch (select){
                    case 1:
                        System.out.println("All Customers");
                        CustomerService.getInstance().getAllCustomers();
                        start();
                        break;
                    case 2:
                        System.out.println("All Rooms");
                        reservationService.printAllRooms();
                        start();
                        break;
                    case 3:
                        System.out.println("All Reservations");
                        ReservationService.getInstance().printAllReservetions();
                        start();
                        break;
                    case 4:
                        addRoom();
                        start();
                        break;
                    case 5:
                        System.out.println("Exit to Main Menu? y/n");
                        char gender = scanner.next().charAt(0);
                        if (gender == 'y'){
                            mainMenu.start();
                            //System.exit(0);
                            exit = true;
                        }
                        start();
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } while (!scanner.equals("1") && !scanner.equals("2") && !scanner.equals("3") && !scanner.equals("4") && !scanner.equals("5") && !exit);
        }

    }
    public static void addRoom() throws NumberFormatException, InputMismatchException {
        //add room number
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a new Room number");
        String roomNum = input.nextLine();
        try {
            Integer.parseInt(roomNum);
        } catch (NumberFormatException n) {
            System.out.println("Numeric input only");
            adminMenu();
        }

        //add room price
        System.out.println("Enter Room Price");
        double price = 0;
        try {
            price = input.nextDouble();
        } catch (NumberFormatException n) {
            System.out.println("Numeric input only");
            while (price == 0) {
                System.out.println("Try again");
            }
        }

        //add room type
        System.out.println("Add Room Type Single(1) or Double(2)");
        RoomType roomType = null;
        try {
            roomType = input.nextInt() == 1 ? RoomType.SINGEL : RoomType.DOUBLE;
        } catch (InputMismatchException n) {
            System.out.println("Numeric input only");
            addRoom();
            while (roomType == null) {
                System.out.println("Please Enter a Room Type");
                addRoom();
            }
        }
        IRoom room;
        room = new Room(roomNum,price,roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);
        adminResource.addRoom(rooms);

        System.out.println("Do You want to add another Room? -- y/n --");
        String option = input.next();
        switch (option){
            case "y":
                addRoom();
            case "n":
                adminMenu();
        }
    }

    public static int adminMenu(){
        System.out.println("Admin menu");
        System.out.println("-------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-------------------------");
        System.out.println("Please select menu number:");
        Scanner sc = new Scanner(System.in);
        int select = sc.nextInt();
        return select;
    }
}
