/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.domains.Order;
import com.app.domains.OrderProduct;
import com.app.util.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lovreone
 */
public class OrderDao {
    
    public Session session; 
    Transaction tx;
    
    public void startSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public void stopSession() {
        session.close();
    }
    
    // Adds an Order to DB (UserRegistration servlet) - WORKS
    public boolean createOrder(Order order) {
        int orderId = 0;
        startSession();
        try {
            tx = session.beginTransaction();
            orderId = (int) session.save(order);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        } 
        
        return orderId > 0;
    
    }
    
    // Retrieves one Order from DB - WORKS
    public Order getSingleOrder(String orderId) {
        Order order = new Order();
        startSession();
        try {
            tx = session.beginTransaction();
            order = (Order) session.get(Order.class, Integer.parseInt(orderId));
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return order;
    }   
    
    // Retrieves Active (Pending) Order from DB - WORKS
    public Order getActiveOrder(String userId) {
        Order order = new Order();
        startSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Order o where o.user=:user and o.status='pending'");
            query.setString("user", userId);
            order = (Order) query.list().get(0);      
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return order;
    }   
    
    // Check if order_product table already contains a row with Order/Product key combination
    public boolean checkIfDuplicateRow(int orderId, int productId) {
        int num = 0;
        BigInteger bigInt = BigInteger.ZERO;
        startSession();
        try {
            tx = session.beginTransaction();
            // Cant access OrderProduct key subitems (FK columns)using HQL
            /*Query query = session.createQuery("select count(*) from OrderProduct od where od.orderid=:order and od.productid=:product");
            query.setString("order", String.valueOf(orderId));
            query.setString("product", String.valueOf(productId));
            num = (Long)query.uniqueResult();*/
             
            SQLQuery sqlQuery = session.createSQLQuery("SELECT count(*) from order_product"
                    + " WHERE order_id=" + orderId + " AND product_id=" + productId + ";");
            bigInt = (BigInteger) sqlQuery.uniqueResult();
            num = bigInt.intValue();
            
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
      
    public String addToCart(OrderProduct op) {
        String result = "~";
        int orderId = op.getPk().getOrder().getOrderId();
        int productId = op.getPk().getProduct().getProductId();
        
        startSession();
        try {
            tx = session.beginTransaction();
            
            /*if (checkIfDuplicateRow(orderId, productId)) {     
                result = String.valueOf(session.merge(op)); // TESTIRATI STA MERGE RADI
            } else {
                result = String.valueOf(session.save(op)); // RADI VAN USLOVA - DOdaje novi red u bazu
            }*/
            result = String.valueOf(session.save(op));
            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
 
        return result;
    }
    
    
    
    public String mergeObjectTest(OrderProduct op) {
        String result = "~";
        startSession();
        try {
            tx = session.beginTransaction();
            
                result = String.valueOf(session.merge(op)); // TESTIRATI STA MERGE RADI
            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return result;
    }
    
    // Updates Order in DB - WORKS
    public String updateOrder(Order order) {
        String status = "";
        startSession();
        try {
            tx = session.beginTransaction();
            session.update(order);
            status += "Successful DB insert!";
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + ex);
        } finally {
            stopSession();
        }
        return status;
    }
    
    // Deletes an Order from DB, TEST OUT FURTHER (ManageProducts, cms-product-manage.jsp)    
    public void deleteOrder(String orderId) {
        startSession();
        try {
            tx = session.beginTransaction();
            Order order = (Order) session.load(Order.class, Integer.parseInt(orderId));
            session.delete(order);
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
