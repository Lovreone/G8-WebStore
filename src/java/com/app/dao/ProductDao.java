/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.domains.Product;
import com.app.util.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lovreone
 */
public class ProductDao {
    
    public Session session; 
    Transaction tx;
    
    // IN CASE I NEED MANUAL SESSION MANAGEMENT(TO AVOID) 
    public void startSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public void stopSession() {
        session.close();
    }
    
    // Adds a product to DB, TEST OUT FURTHER (cms-product-create.jsp)
    public boolean createProduct(Product product) {
        int productId = 0;
            startSession();
        try {
            tx = session.beginTransaction();
            productId = (int) session.save(product);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        } 
        return productId > 0;
    }
    
    // Retrieves list of all Products from DB, TEST OUT FURTHER (product-list.jsp) 
    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();
        startSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Product");
            for (Object o : query.list()) {
                products.add((Product) o);
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
        return products;
    }
    
    // Retrieves one Product from DB, TEST OUT FURTHER (product-single.jsp) 
    public Product getSingleProduct(String productId) {
        Product product = new Product();
        startSession();
        try {
            tx = session.beginTransaction();
            product = (Product) session.get(Product.class, Integer.parseInt(productId));
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return product;
    }   
    
    // Updates Product in DB, TEST OUT FURTHER, REDEFINE RETURN IN NECESSARY 
    // (ManageProducts, cms-product-manage.jsp)   
    public void updateProduct(Product product) {
        startSession();
        try {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
    }
    
    // Deletes a Product from DB, TEST OUT FURTHER (ManageProducts, cms-product-manage.jsp)    
    public void deleteProduct(String productId) {
        startSession();
        try {
            tx = session.beginTransaction();
            Product product = (Product) session.load(Product.class, Integer.parseInt(productId));
            session.delete(product);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
    }
    
}
