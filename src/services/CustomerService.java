package services;

import model.Customer;
import model.Reservation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomerService {
    private static CustomerService customerService = null;
    Set<Customer> customers = new HashSet<>();
    public static ReservationService reservationService = ReservationService.getInstance();
    private CustomerService() {}

    public static CustomerService getInstance() {
        if (null == customerService) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String firstName,String lastName, String email){
        Customer customer = new Customer(firstName,lastName,email);
        customers.add(customer);
    }

    public Customer getCustomer(String customerEmail){
        for (Customer customer : customers) {
            if (!customer.equals(customer.getEmail())){
                System.out.println(customerEmail);
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        if (!customers.isEmpty()){
            customers.forEach(System.out::println);
        }
        else {
            System.out.println("There are no new Accounts");
        }
        return customers;
    }

    public static Collection<Reservation> getCustomersReservation(String customerEmail) {
        return reservationService.getCustomersReservations(customerEmail);
    }

}
