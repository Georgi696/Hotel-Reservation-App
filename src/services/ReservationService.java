package services;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService reservationService = null;
    Set<IRoom> roomList = new HashSet<>();
    Set<Reservation> reserveList = new HashSet<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (null == reservationService) {
            return new ReservationService();
        }
        return reservationService;
    }



    public void addRoom(IRoom room){
        roomList.add(room);
    }

    public IRoom getARoom(String roomId){
        for (IRoom room: roomList) {
             if (room.getRoomNumber().equals(roomId)){
                 return room;
             }
        }
        return null;
    }

    public Reservation reserveARoom(String customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservationRoom = new Reservation();
        reserveList.add(reservationRoom);
        return reservationRoom;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        HashSet<Reservation> reservation = new HashSet<>();
        for (Reservation r: reserveList){
            if (r.equals(customer)){
                reservation.add(r);
            }
        }
        return reservation;
    }

    public Collection<IRoom> getAllRooms() {
        return roomList;
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
}
