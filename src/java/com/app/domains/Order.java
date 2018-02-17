/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int orderId;
    
    @Column(name = "status")
    private String status; // pending OR completed
    
    @Column(name = "total_price")
    private int totalPrice;
    
    @Column(name = "timestamp")
    private String timeStamp;
    /* TRY AT LATER STAGE
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();
    */
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private OrderDetails orderDetails; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private User user; 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.order")
    private Set<OrderProduct> orderProducts = new HashSet<OrderProduct>(0); // ManyToMany

    public Order(String status, int totalPrice, String timeStamp) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.timeStamp = timeStamp;
    }
    
    public Order() {
    }

    @Override
    public String toString() {
        return "ORDER: id: " + orderId + ", status: " + status + ", totalPrice: " + totalPrice + ", timeStamp: " + timeStamp;
    }   

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
    
}
