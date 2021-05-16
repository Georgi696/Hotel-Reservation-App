package services;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {
    
    private static ReservationService reservationService = null;
    Collection<IRoom> roomList = new HashSet<>();
    Collection<Reservation> reserveList = new HashSet<>();

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

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
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
        for (Reservation reservation: reserveList) {
            System.out.println(reservation);
        }
    }
}
