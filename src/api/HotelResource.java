package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.util.*;

public class HotelResource {
    private static HotelResource hotelResource;
    public static ReservationService reservationService = ReservationService.getInstance();
    private HotelResource() {}


    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email){
        return CustomerService.getInstance().getCustomer(email);
    }

    public void createACustomer(String firstName, String lastName, String email){
        CustomerService.getInstance().addCustomer(firstName,lastName,email);
    }

    public IRoom getRoom(String roomNumber){
        return ReservationService.getInstance().getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room,
                                Date checkInDate, Date checkOutDate){
        CustomerService customer = CustomerService.getInstance();
        return ReservationService.getInstance().reserveARoom(customer.getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

}
