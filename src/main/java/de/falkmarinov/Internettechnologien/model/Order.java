package de.falkmarinov.Internettechnologien.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Long id;
    private Customer customer;
    private String street;
    private String zip;
    private String place;
    private String paymentMethod;
    private String orderDate;
    private Map<Long, Integer> positions = new HashMap<>();

    public void addPosition(Long bookId, Integer amount) {
        positions.put(bookId, amount);
    }

    public Map<Long, Integer> getPositions() {
        return positions;
    }

    public void setPositions(Map<Long, Integer> positions) {
        this.positions = positions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
