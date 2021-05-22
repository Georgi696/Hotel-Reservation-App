package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import services.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final AdminMenu adminMenu = AdminMenu.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    private static final HotelResource hotelResource = HotelResource.getInstance();

    private static MainMenu mainMenu;
    private String emailInput;

    public static MainMenu getInstance() {
        if (null == mainMenu) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }


        public static void createAccount() throws ParseException {
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

        public static void createRoom(){
            Scanner input = new Scanner(System.in);
            String book;
            String account;
            Calendar calendar = Calendar.getInstance();
            final String DATE_PATTERN = "MM/dd/yyyy";
            SimpleDateFormat date = new SimpleDateFormat(DATE_PATTERN);
            try {
                System.out.println("Enter CheckIn Date mm/dd/yyyy:");
                Date todayDate = date.parse(date.format(calendar.getTime()));
                Date checkInDate = new SimpleDateFormat(DATE_PATTERN).parse(input.nextLine());
                System.out.println("Enter CheckOut Date mm/dd/yyyy:");
                Date checkOutDate = new SimpleDateFormat(DATE_PATTERN).parse(input.nextLine());
                if (!checkInDate.before(todayDate) && !checkOutDate.before(checkInDate)) {
                    Collection<IRoom> rooms = reservationService.findARoom(checkInDate, checkOutDate);
                    if (!rooms.isEmpty()) {
                        do {
                            System.out.println("Would you like to book a room? y/n");
                            book = input.next().toLowerCase().trim();
                            if (book.equals("y")) {
                                do {
                                    System.out.println("Do you have an account with us? y/n");
                                    account = input.next().toLowerCase().trim();
                                    if (account.equals("y")) {
                                        input.nextLine();
                                        System.out.println("Enter Email format: name@domain.com");
                                        String email = input.next();
                                        Customer customer = hotelResource.getCustomer(email);
                                        System.out.println("What room number would you like to reserve?");
                                        rooms.forEach(System.out::println);
                                        String roomNumber = input.next();
                                        IRoom room = hotelResource.getRoom(roomNumber);
                                        hotelResource.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
                                        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
                                        System.out.println(reservation);
                                        start();
                                    } else if (account.equals("n")) {
                                        System.out.println("You have to create an account");
                                        createAccount();
                                    } else {
                                        System.out.println("Invalid input!");
                                        createRoom();
                                    }
                                } while (!account.equals("y") && !account.equals("n"));
                            } else if (book.equals("n")) {
                                start();
                            } else {
                                System.out.println("Invalid input!");
                                createRoom();
                            }
                        } while (!book.equals("y") && !book.equals("n"));
                    } else {
                        System.out.println("There is no room available");
                        start();
                    }
                } else {
                    System.out.println("Check in date needs to be from today onwards and check out date can not be before check in date");
                    createRoom();
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }


        public static void seeReservations() throws ParseException {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your email: ");
            String email = input.next();
            Collection<Reservation> reservations = reservationService.getCustomersReservations(hotelResource.getCustomer(email));
            if (!reservations.isEmpty()) {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            } else {
                System.out.println("You have no reservations yet");
                start();
            }
        }

    public static void start() throws ParseException{
        Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    while (!exit) {
        do {
                System.out.println("Welcome to the Hotel Reservation App");
                System.out.println("-------------------------");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservation");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("-------------------------");
                System.out.println("Please select menu number:");
                String select = scanner.next();
                switch (select) {
                    case "1":
                        createRoom();
                        break;
                    case "2":
                        seeReservations();
                        break;
                    case "3":
                        createAccount();
                        break;
                    case "4":
                        adminMenu.start();
                        break;
                    case "5":
                        System.out.println("Exit? y/n");
                        char yesNo = scanner.next().charAt(0);
                        if (yesNo == 'y') {
                            System.out.println("Exit");
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } while (!scanner.equals("1") && !scanner.equals("2") && !scanner.equals("3") && !scanner.equals("4") && !scanner.equals("5") && !exit);
        }
    }
}
