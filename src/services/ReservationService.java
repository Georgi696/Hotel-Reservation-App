package services;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService reservationService = null;
    private static final int RECOMMENDED_ROOMS = 7;
    //public static final CustomerService customerService = CustomerService.getInstance();
    private ReservationService() {}

    public static ReservationService getInstance() {
        if (null == reservationService) {
            return new ReservationService();
        }
        return reservationService;
    }

    static Collection<IRoom> roomList = new ArrayList<>();
    static Collection<Reservation> reserveList = new ArrayList<>();

    public IRoom getARoom(String roomId){
        for (IRoom room: roomList) {
             if (room.getRoomNumber().equals(roomId)){
                 return room;
             }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservationRoom = new Reservation(customer,room,checkInDate,checkOutDate);
        Collection<Reservation> customerReservations = getCustomersReservations(customer);
        if (customerReservations == null){
            customerReservations = new HashSet(){};
        }
        reserveList.add(reservationRoom);
        return reservationRoom;
    }

    Collection<Reservation> getCustomerReservation(Customer customer){
        Set<Reservation> reservation = new HashSet<>();
        for (Reservation r: reserveList){
            if (r.equals(customer)){
                reservation.add(r);
            }
        }
        return reservation;
    }


    public void printAllReservetions(){
        if (!reserveList.isEmpty()) {
            System.out.println(reserveList);
        } else {
            System.out.println("There is no reservations yet");
        }
    }


    public static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new HashSet<>();
        if (reserveList.size() == 0) {
            availableRooms = roomList;
            return availableRooms;
        } else {
            for (Reservation res : reserveList) {
                for (IRoom rom : roomList) {
                    if ((rom.getRoomNumber().equals(res.getRoom().getRoomNumber()))
                            && ((checkInDate.before(res.getCheckInDate()) && checkOutDate.before(res.getCheckInDate()))
                            || (checkInDate.after(res.getCheckOutDate()) && checkOutDate.after(res.getCheckOutDate())))
                            || (!res.getRoom().getRoomNumber().contains(rom.getRoomNumber()))) {
                        availableRooms.add(rom);
                        System.out.println("Room Available?" + availableRooms);
                    } else if (rom.getRoomNumber().equals(res.getRoom().getRoomNumber())) {
                        availableRooms.remove(rom);
                    }
                }
            }
        }
        recommend(availableRooms,checkInDate, checkOutDate);
        return availableRooms;
    }

    void addRoom(IRoom room){
        roomList.add(room);
    }

    public void printAllRooms(){
        if (!roomList.isEmpty()) {
            System.out.println(roomList);
        } else {
            System.out.println("There are no rooms.");
        }
    }

    public static Collection<Reservation> getCustomersReservations(Customer customer) {
        if (customer != null){
            CustomerService.getInstance().getCustomer(customer.getEmail());
        }
        else {
            System.out.println("No such email");
        }
        return reserveList;
    }

    public static void recommend(Collection<IRoom> availableRooms,Date checkInDate, Date checkOutDate){
        for(Reservation res : reserveList){
            for(IRoom room : roomList){
                if(room.getRoomNumber().equals(res.getRoom().getRoomNumber())
                        && !((checkInDate.before(res.getCheckInDate()) && checkOutDate.before(res.getCheckInDate()))
                        || (checkInDate.after(res.getCheckOutDate()) && checkOutDate.after(res.getCheckOutDate()))))
                    availableRooms.remove(room);
                System.out.println(availableRooms);
            }
        }
    }

    public Collection<IRoom> getAllRooms() {
        return roomList;
    }

}
