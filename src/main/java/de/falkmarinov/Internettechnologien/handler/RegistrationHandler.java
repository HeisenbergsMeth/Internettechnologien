package de.falkmarinov.Internettechnologien.handler;

import de.falkmarinov.Internettechnologien.model.Customer;
import de.falkmarinov.Internettechnologien.repository.CustomerDao;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("registrationHandler")
@RequestScoped
public class RegistrationHandler implements Serializable {

    @Inject
    @Named("customerDao")
    private CustomerDao customerDao;

    private String name;
    private String surname;
    private String email;
    private String password;

    public String register() {
        boolean exist = customerDao.findUserByEmail(email).isPresent();

        if (exist) {
            sendMessage(FacesMessage.SEVERITY_ERROR, "Email wird bereits verwendet", "");
            return "register.xhtml";
        } else {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setEmail(email);
            customer.setPassword(password);

            customerDao.save(customer);

            sendMessage(FacesMessage.SEVERITY_INFO, "Deine Registrierung war erfolgreich", "");

            resetForm();

            return "login.xhtml";
        }
    }

    private void sendMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    private void resetForm() {
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
