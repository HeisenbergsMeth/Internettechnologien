package de.falkmarinov.Internettechnologien.handler;

import de.falkmarinov.Internettechnologien.model.Customer;
import de.falkmarinov.Internettechnologien.repository.Dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Named("registrationHandler")
@RequestScoped
public class RegistrationHandler  implements Serializable {

    @Inject
    @Named("customerDao")
    private Dao<Customer> customerDao;

    private String name;
    private String surname;
    private String email;
    private String password;

    public void register() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setEmail(email);
        customer.setPassword(password);

        reset();

        customerDao.save(customer);
    }

    private void reset() {
        name = "";
        surname = "";
        email = "";
        password = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
