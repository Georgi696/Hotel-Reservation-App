package services;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService reservationService = null;
    public static final CustomerService customerService = CustomerService.getInstance();

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
        reserveList.add(reservationRoom);
        return reservationRoom;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
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


    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        if (reserveList.isEmpty()) {
            return roomList;
        } else {
            for (Reservation reservation : reserveList) {
                if (!reservation.getCheckInDate().after(checkInDate) && !reservation.getCheckOutDate().before(checkOutDate)) {
                    for (IRoom room : roomList) {
                        if (!reservation.getRoom().equals(room)) {
                            availableRooms.add(room);
                        }
                    }
                }
            }
        }
        return availableRooms;
    }

    public void addRoom(IRoom room){
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
        CustomerService.getInstance().getCustomer(customer.getEmail());
        return reserveList;
    }


    public Collection<IRoom> getAllRooms() {
        return roomList;
    }

}
