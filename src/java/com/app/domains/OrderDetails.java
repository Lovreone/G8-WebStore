/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {
    
    // SHIPPING INFO
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "street_address")
    private String streetAddress; // Street Address, Bulding Number, Appartment number
    
    // BILLING INFO
    @Column(name = "cc_type")
    private String ccType; // Visa, MasterCard
   
    @Column(name = "cc_number")
    private String ccNumber; // 16 num chars
   
    @Column(name = "exp_month")
    private int expMonth; // MM
    
    @Column(name = "exp_year")
    private int expYear; // YYYY
    
    @Column(name = "card_holder")
    private String cardHolder;
    
    @Column(name = "cvv_number")
    private int cvvNumber; // 3 num chars (AmericanExpress is 4)
    
    @Id
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order; // OneToOne

    public OrderDetails(String firstName, String lastName, String city, String country, String streetAddress, String ccType, String ccNumber, int expMonth, int expYear, String cardHolder, int cvvNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.streetAddress = streetAddress;
        this.ccType = ccType;
        this.ccNumber = ccNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cardHolder = cardHolder;
        this.cvvNumber = cvvNumber;
    }
    
    public OrderDetails() {
    }
    
    @Override
    public String toString() {
        return "ORDER_DETAILS: id: " + order.getOrderId() + ", First name: " + firstName + 
                ", Last name: " + lastName + ", City: " + city + ", Country: " + country + 
                ", Street address: " + streetAddress + ", CC type: " + ccType + 
                ", CC number: " + ccNumber + ", Exp month: " + expMonth + ", Exp year: " + expYear +
                ", Card holder: " + cardHolder + ", CVV number: " + cvvNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    
    
}
