package model;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Customer  {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String regex = "^(.+)@(.+)$";
    private final Pattern pattern = Pattern.compile(regex);


    public Customer(String firstName, String lastName, String email) {
        // TODO Auto-generated constructor stub
        if (isValid(email)) {
            System.out.println("Valid email");
            System.out.println(email);
        }
        else {
            System.out.println("Not a valid email");
            System.out.println("Please enter a valid email");
            System.out.println("You will be now redirected to the Main Menu");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "First Name " + firstName + " Last Name " + lastName + " Email " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(regex, customer.regex) && Objects.equals(pattern, customer.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, regex, pattern);
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}

