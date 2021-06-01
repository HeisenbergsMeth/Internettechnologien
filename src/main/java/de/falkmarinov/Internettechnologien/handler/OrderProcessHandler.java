package de.falkmarinov.Internettechnologien.handler;

import de.falkmarinov.Internettechnologien.model.Customer;
import de.falkmarinov.Internettechnologien.model.Order;
import de.falkmarinov.Internettechnologien.model.ShoppingCart;
import de.falkmarinov.Internettechnologien.repository.Dao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("orderProcessHandler")
@SessionScoped
public class OrderProcessHandler implements Serializable {

    @Inject
    @Named
    private Dao<Order> orderDao;

    @Inject
    private UserSessionHandler userSessionHandler;

    @Inject
    private ShoppingCart shoppingCart;

    private String street;
    private String zip;
    private String place;
    private String paymentMethod;
    private String cardNo;
    private String cardExpirationDate;

    private String processStep;

    public OrderProcessHandler() {
        paymentMethod = "Rechnung";
        processStep = "/WEB-INF/facelets/orderInput.xhtml";
    }

    public String checkout() {
        Order order = new Order();
        order.setStreet(street);
        order.setZip(zip);
        order.setPlace(place);
        order.setPaymentMethod(paymentMethod);
        order.setCustomer(userSessionHandler.getLoggedInCustomer());
        order.setPositions(shoppingCart.getPositions());
        order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        orderDao.save(order);

        this.reset();
        shoppingCart.reset();

        return "confirmation.xhtml";
    }

    public void reset() {
        street = "";
        zip = "";
        place = "";
        paymentMethod = "Rechnung";
        cardNo = "";
        cardExpirationDate = "";

        stepBack();
    }

    public void nextStep() {
        String nextStep;

        if (processStep.contains("orderInput")) {
            nextStep = "orderCheck";
        } else {
            nextStep = "orderInput";
        }

        processStep = String.format("/WEB-INF/facelets/%s.xhtml", nextStep);
    }

    public void stepBack() {
        String nextStep;

        if (processStep.contains("orderCheck")) {
            nextStep = "orderInput";
        } else {
            nextStep = "orderCheck";
        }

        processStep = String.format("/WEB-INF/facelets/%s.xhtml", nextStep);
    }

    public String getProcessStep() {
        return processStep;
    }

    public void setProcessStep(String processStep) {
        this.processStep = processStep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }
}
