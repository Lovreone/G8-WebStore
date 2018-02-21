/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.OrderDao;
import com.app.dao.ProductDao;
import com.app.dao.UserDao;
import com.app.domains.Order;
import com.app.domains.OrderDetails;
import com.app.domains.OrderProduct;
import com.app.domains.Product;
import com.app.domains.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lovreone
 */
@WebServlet(name = "CreateOrder", urlPatterns = {"/CreateOrder"})
public class CreateOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // THIS SERVLET IS FOR TEST USES ONLY, DELETE LATER IF NECESSARY
        // FE TESTS: http://localhost:8080/G8-WebStore/a-order-tests.jsp
        int qty = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("productid"));
        String userId = request.getParameter("userid");
        
        PrintWriter out = response.getWriter();
        out.println("GENERAL STATUS LOG:\n Selected qty: " + qty + ",\n ProductID: " + productId + 
                ",\n LoggedUserID: " + (!userId.isEmpty() ? userId : "NOT LOGGED IN!") + "\n");
        out.println("-------------------------------------------");
        
        /*#################################################
            GENERAL IDEA BEHIND SHOPPING PROCESS WORKFLOW:
          #################################################
            1. User Registers:
                New User added to database;
                'pending' Order created automatically for that User;

            2. User AddsProductToCart (Product page):
                New OrderProduct row is created with selected Qty in DB;
            3. User AddsSameProductToCartAgain (Product page):
                Existing OrderProduct row is updated with new Qty in DB;
                *** It should work like this: when user adds 3 products to cart 
                from product page, and in cart we already had 5 of that same product, 
                cart will be updated to have the newer qty value for that product(3).
                This is the way it works on AliExpress. ***
            4. User ChangesProductQty (Cart page):
                Existing OrderProduct row is updated with new Qty in DB;
            5. User RemovesProductFromCart (Cart page):
                Existing OrderProduct row is deleted from DB;

            6. User CompletesOrder:
                Current 'pending' Order for that User changed to 'completed'; 
                New 'pending' Order created for that User;
        ################################################# */
        
        // GENERAL ITEMS USED FOR DATABASE OPERATIONS
        OrderDao oDao = new OrderDao();
        UserDao uDao = new UserDao();
        
        /*#################################################
           CREATE A NEW ORDER MANUALLY - TEST (Must be Logged in!) - WORKS!
          #################################################
        Desc: Manual creation of an Order, where we create a new Order object,
        get existing (Logged) User, connect User and Order and save Order to DB
        Note: tests if oDao.createOrder(order) method works properly, and if 
        Hibernate mapping between User and Order (OneToMany) is done properly.
        This scenario will probably not be used in final version
        #################################################
        
        Order order = new Order("pending", 0, "00.00.0000 00:00");
        User u;      
        if (!userId.isEmpty()) {
            u = uDao.getSingleUser(userId);  
            order.setUser(u);
        }
        boolean status = oDao.createOrder(order);
        out.println("CREATE ORDER - CHANGES:\nIs Order creation successful? " + status + 
                ",\n orderId is: " + order.getOrderId());
        */ 
        
        
        /*#################################################
           6. COMPLETE EXISTING ORDER > CREATES NEW PENDING ORDER - TEST (Must be Logged in!) - WORKS!
          #################################################
        Desc: Getting an existing active Order from DB by logged in User's id. Method should
        return the "pending" Order for that User, which should be only 1 result.
        Creation of OrderDetails object (needed for Order completion), linking Order
        with OrderDetails, updating status to "completed". Updating exising Order
        will both Update status and create a new OrderDetails linked to it. After
        existing Order is "completed" we create a new "pending" Order for that User.
        Note: This logic tests if Hibernate mapping between Order and OrderDetails
        (OneToOne) is implemented correctly. This logic will be used at the end 
        of the shopping proces when a User completes an order.
        ################################################# 
        
        Order order = oDao.getActiveOrder(userId);
        OrderDetails od = new OrderDetails("Pera", "Peric", "Beograd", "Serbia", "Petra Lekica 3/2", "Visa", "1234567890123456", 12, 2019, "Pera Peric", 123);
        order.setOrderDetails(od);
        od.setOrder(order);
        order.setStatus("completed");
        oDao.updateOrder(order);
        out.println("UPDATE ORDER - CHANGES:\nWhich Order is updated in DB as 'completed'?\n\t " + oDao.getSingleOrder(String.valueOf(order.getOrderId())).toString() + 
                ",\nOrder's Details saved in DB are:\n\t " + oDao.getSingleOrder(String.valueOf(order.getOrderId())).getOrderDetails().toString() + "\n");
        od = null;
        order = new Order("pending", 0, "00.00.0000 00:00");
        User u = uDao.getSingleUser(userId);  
        order.setUser(u);
        boolean status = oDao.createOrder(order);
        out.println("CREATE ORDER - CHANGES:\nIs new 'pending' Order creation for this User successful? " + status + 
                ",\nNew 'pending' Order created in DB: \n\t" + order.toString() );
        */
       
        
        
        
        /*#################################################
           ADD PRODUCT TO CART - TEST (Must be Logged in!)
          #################################################
        Desc: 
        Note: 
        ################################################# */
        
        
        
        
        // znamo usera (get user), znamo order (get order)
        // znamo proizvod > dodajemo proizvod u order
            // oduzimamo proizvod iz ordera
        // listamo order da vidimo promene
        
        // ADDS PRODUCT TO CART (CREATES NEW OD ROW IN DB)
        /*ProductDao pDao = new ProductDao();
        Order order = oDao.getOrderByStatus("8", "pending"); // WORKS - out.println(order.toString());
        Product product = pDao.getSingleProduct("13"); // WORKS - out.println(product.toString());
        OrderProduct op = new OrderProduct();
            op.setOrder(order);
            op.setProduct(product);
            op.setProductQty(qty);
        // String result = oDao.addToCart(op); // SAVES
        String result = oDao.mergeObjectTest(op); // MERGES
        out.println("result: " + result);
        out.println("OP: " + op);*/
        
        
        
        
        // CHECKS IF ROW WITH KEY EXISTS IN DB
        // out.println("Do we have more that 0 rows in DB for (orderid=12 and productid=13): " + oDao.checkIfDuplicateRow(11, 13));
        
        
        
        
        
        //order.setTimeStamp("Promenjeno");
        //oDao.updateOrder(order); // WORKS INDEPENDENTLY (UPDATE)
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
