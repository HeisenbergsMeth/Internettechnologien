package de.falkmarinov.Internettechnologien.handler;

import de.falkmarinov.Internettechnologien.model.Customer;
import de.falkmarinov.Internettechnologien.repository.CustomerDao;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named("userSessionHandler")
@SessionScoped
public class UserSessionHandler implements Serializable {

    @Inject
    @Named("customerDao")
    private CustomerDao customerDao;

    @Inject
    private OrderProcessHandler orderProcessHandler;

    private Customer loggedInCustomer;
    private boolean loggedIn;

    private String email;
    private String password;

    public UserSessionHandler() {
        loggedInCustomer = new Customer();
        loggedIn = false;
    }

    public String login() {
        Optional<Customer> optionalCustomer = customerDao.findUserByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getPassword().equals(password)) {
                loggedInCustomer = customer;
                loggedIn = true;
                sendMessage(FacesMessage.SEVERITY_INFO, "Hallo " + customer.getName(), "");
                resetForm();
                return "user.xhtml";
            } else {
                sendMessage(FacesMessage.SEVERITY_ERROR, "Passwort ungültig", "");
            }
        } else {
            sendMessage(FacesMessage.SEVERITY_ERROR, "Email nicht registriert", "");
        }
        return "login.xhtml";
    }

    public String logout() {
        loggedInCustomer = new Customer();
        loggedIn = false;
        orderProcessHandler.reset();
        return "login.xhtml";
    }

    private void sendMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    private void resetForm() {
        email = "";
        password = "";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
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
