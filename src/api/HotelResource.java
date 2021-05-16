package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;

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

    public Reservation bookARoom(Customer customerEmial, IRoom room,
                                Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().reserveARoom(customerEmial,room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservation(Customer customerEmail){
        return null;
    }

    public Collection<IRoom> findATRoom(Date checkInDate, Date checkOutDate){
        return null;
    }


}
