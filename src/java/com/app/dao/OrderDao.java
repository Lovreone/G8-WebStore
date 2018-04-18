/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.domains.Order;
import com.app.domains.OrderProduct;
import com.app.domains.Product;
import com.app.util.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    
    // Adds an Order to DB (UserRegistration servlet) - WORKS, USED
    // Used for Order creation & Order completion logic
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
    
    // Retrieves specific Order from DB - WORKS, NOT USED
    // Will be used for Dashboard (User,Admin) later
    // THROWS STACKOVERFLOW ERROR - INVESTIGATE FURTHER!!!
    public Order getSingleOrder(int orderId) {
        Order order = new Order();
        startSession();
        try {
            tx = session.beginTransaction();
            order = (Order) session.get(Order.class, orderId);
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
    
    // Retrieves Active(Pending) Order from DB for User - WORKS, USED
    // Used for Order completion logic, will also be used on Shopping cart page
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
    
    // Updates an Order in DB - WORKS, USED
    // Used for Order Completion logic where we save OrderDetails with it
    // Used for AddToCart logic, where we save/update OrderProduct with it
    public String updateOrder(Order order) {
        String status = "";
        startSession();
        try {
            tx = session.beginTransaction();
            session.update(order);
            status += "Successful DB update!";
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
    
    // Check if order_product table already contains a row with Order/Product key combination - WORKS, NOT USED
    // It is currently not and probably will not be used in app logic, unless for manual tests 
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
    
    
    // Checks if OrderDetails row exists in database for specific Order - WORKS, USED
    // Used on Shipping info page to check if info was prevoiusly saved (store-shipping.jsp)
    public boolean orderDetailsExist(int orderId) {
        int num = 0;
        BigInteger bigInt = BigInteger.ZERO;
        startSession();
        try {
            tx = session.beginTransaction();
                        
            SQLQuery sqlQuery = session.createSQLQuery("SELECT count(*) from order_details"
                    + " WHERE order_id=" + orderId + ";");
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
    
    // Calculates and returns total cart price based on provided (pending)Order - WORKS, USED
    // It is used on CompleteOrder servlet, when order is completed, price is calculated and saved to db.
    public int getTotalCartPrice(Order order) {
        int totalPrice = 0;
        
        Set<OrderProduct> cartItemsList = order.getOrderProducts();
        for (OrderProduct singleProduct : cartItemsList) {
            totalPrice += singleProduct.getPk().getProduct().getUnitPrice() * singleProduct.getProductQty(); 
        }
        
        return totalPrice;
    }
    
        
    
    
    
    
    // ############ PROTOTYPES BELOW - DELETE WHEN DEEMED UNNECESSARY ############
   
    
    
    
    // Method should have add Product to cart, but is inconclusive - INCONCLUSIVE, NOT USED
    // INCOMPLETE, WORKS DIRECTLY WITH ORDERPRODUCT ITEMS AND WILL PROBABLY NOT BE USED
    public String addToCart(OrderProduct op) {
        String result = "~";
        int orderId = op.getPk().getOrder().getOrderId();
        int productId = op.getPk().getProduct().getProductId();
        
        startSession();
        try {
            tx = session.beginTransaction();
            
            /*if (checkIfDuplicateRow(orderId, productId)) {     
                result = String.valueOf(session.merge(op)); // Test behaviour of merge
            } else {
                result = String.valueOf(session.save(op)); // Works outide of IF - Adds new row to DB
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
    
    //INCOMPLETE, WORKS DIRECTLY WITH ORDERPRODUCT ITEMS AND WILL PROBABLY NOT BE USED
    public String mergeObjectTest(OrderProduct op) {
        String result = "~";
        startSession();
        try {
            tx = session.beginTransaction();
            
                result = String.valueOf(session.merge(op)); // TEST WHAT MERGE DOES
            
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
    
    // Deleting an Order from DB is not and will not be possible - TESTED, WORKS 
  
    
    
    
}
