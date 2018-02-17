/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.domains.User;
import com.app.util.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lovreone
 */
public class UserDao {
    
    Session session;
    Transaction tx;
    
    // IN CASE I NEED MANUAL SESSION MANAGEMENT(TO AVOID) 
    public void startSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public void stopSession() {
        session.close();
    }
    
    // Receives user, Saves in DB, returns USER ID
    public int createUser(User user) {
        int userId = 0;
        startSession();
        try {
            tx = session.beginTransaction();
            userId = (int) session.save(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        } 
        return userId;
    }
    
    // Returns # od rows which contain email received as argument F(0) OR T(1)
    public boolean isExistingUser(String email) {
        long num = 0;
        startSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from User u where u.email=:email");
            query.setString("email", email);
            num = (Long)query.uniqueResult();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return num > 0;
    }
    
    // Checks if login credentials are valid in DB
    public List<User> loginCheck(String email, String password) {
        List<User> users = new LinkedList<>();
        startSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User u where u.email=:email and u.password=:password");
            query.setString("email", email);
            query.setString("password", password);
            for (Object o : query.list()) {
                users.add((User) o);
            }
            tx.commit(); 
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return users;
    }
    
    // Retrieves specific User from DB
    public User getSingleUser(String userId) {
        User user = new User();
        startSession();
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, Integer.parseInt(userId));
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return user;
    }   
    
}
