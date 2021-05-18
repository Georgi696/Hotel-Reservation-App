package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import services.CustomerService;
import services.ReservationService;

import java.text.ParseException;
import java.util.*;

public class AdminMenu{
    public static AdminResource adminResource = AdminResource.getInstance();
    //public static HotelResource hotelResource = HotelResource.getInstance();
    private static MainMenu mainMenu = MainMenu.getInstance();
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

        switch (select){
            case 1:
                System.out.println("All Customers");
                CustomerService.getInstance().getAllCustomers();
                mainMenu.start();
                break;
            case 2:
                System.out.println("All Rooms");
                seeAllRooms();
                break;
            case 3:
                System.out.println("All Reservations");
                ReservationService.getInstance().printAllReservetions();
                mainMenu.start();
                break;
            case 4:
                addRoom();
                mainMenu.start();
                break;
            case 5:
                System.out.println("Exit to Main Menu? y/n");
                Scanner scanner = new Scanner(System.in);
                char gender = scanner.next().charAt(0);
                if (gender == 'y'){
                    mainMenu.start();
                }
                break;
        }
    }
    public static void addRoom() throws NumberFormatException, InputMismatchException {
        //add room number
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a new Room number");
        String roomNum = input.nextLine();
        int rNum = 0;
        try {
            rNum = Integer.parseInt(roomNum);
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
                addRoom();
            }
        }

        //add room type
        System.out.println("Add Room Type Single(1) or Double(2)");
        RoomType roomType = null;
        try {
            roomType = input.nextInt() == 1 ? RoomType.SINGEL : RoomType.DOUBLE;
        } catch (InputMismatchException n) {
            System.out.println("Numeric input only");
            while (roomType == null) {
                System.out.println("Please Enter a Room Type");
                addRoom();
            }
        }
        IRoom room;
        room = new Room(roomNum,price,roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);

        System.out.println("Do You want to add another Room?");
        String option = input.next();
        switch (option){
            case "y":
                adminResource.addRoom(rooms);
                addRoom();
            case "n":
                adminResource.addRoom(rooms);
                adminMenu();
        }
    }

    public static void seeAllCustomers(){
        Collection<Customer> customers = adminResource.getAllCustomers();
        customers.forEach(System.out::println);
        adminMenu();
    }

    public static void seeAllRooms(){
        Collection<IRoom> allRooms = adminResource.getAllRoom();
        allRooms.forEach(System.out::println);
        System.out.println();
        adminMenu();
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
