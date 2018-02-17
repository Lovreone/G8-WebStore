/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;
    
    private String email;
    private String password;
    
    @Column(name = "is_admin")
    private boolean isAdmin;
    // ...
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetails userDetails;
    
    @OneToMany(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE) 
    private Set<Order> orders;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }
    
    @Override
    public String toString() {
        return "USER: id: " + userId + ", email: " + email + ", pass: " + password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
     
}
