package services;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    private static CustomerService customerService = null;
    HashSet<Customer> customers = new HashSet<>();

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
        return customers;
    }

}
