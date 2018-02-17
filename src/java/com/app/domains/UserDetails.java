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
@Table(name = "user_details")
public class UserDetails implements Serializable {

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    // ...
    
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserDetails(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDetails() {
    }

    @Override
    public String toString() {
        return "USER-DETAILS: id: " + user.getUserId() + ", FirstName: " + firstName + ", LastName: " + lastName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
