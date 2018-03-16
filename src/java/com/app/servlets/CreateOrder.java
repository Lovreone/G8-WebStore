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
import java.util.Iterator;
import java.util.Set;
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
        out.println("------------------------\nGENERAL STATUS LOG:\n------------------------\n"
                + " Selected qty: " + qty + "\n"
                + " ProductID: " + productId + "\n"
                + " LoggedUserID: " + (!userId.isEmpty() ? userId : "NOT LOGGED IN!")
        );
        
        out.println("\n###############################################################\n");
                
        // GENERAL ITEMS USED FOR DATABASE OPERATIONS
        OrderDao oDao = new OrderDao();
        UserDao uDao = new UserDao();
        ProductDao pDao = new ProductDao();
        
        /*#################################################
           1. CREATE A NEW ORDER MANUALLY - TEST (Must be Logged in!) - WORKS!
          #################################################
        Desc: Manual creation of an Order, where we create a new Order object,
        get existing (Logged) User, connect User and Order and save Order to DB
        Note: tests if oDao.createOrder(order) method works properly, and if 
        Hibernate mapping between User and Order (OneToMany) is done properly.
        This scenario will not be used in final version. User registration creates
        a first 'pending' order, use this if you want to avoid registering a new User.
        #################################################
        
        Order order = new Order("pending", 0, "00.00.0000 00:00");
        User u;      
        if (!userId.isEmpty()) {
            u = uDao.getSingleUser(userId);  
            order.setUser(u);
        }
        boolean status = oDao.createOrder(order);
        out.println("------------------------\nCREATE ORDER - CHANGES:\n------------------------\n"
                + "Is Order creation successful? " + status + "\n "
                + "orderId is: " + order.getOrderId() + "\n "
        ); */
        
        /*#################################################
           2. & 3. ADD PRODUCT TO CART
          #################################################
            Migrated to AddToCart servlet
        ################################################# */
              
        /*#################################################
           4. & 5. CHANGE ITEM IN CART (UPDATE/DELETE)
          #################################################
            Migrated to ManageCart servlet
        ################################################# */
        
        /*#################################################
           6. COMPLETE ACTIVE ORDER > CREATE NEW PENDING ORDER
          #################################################
            Migrated to CompleteOrder servlet
        ################################################# */
                
        
        
        
        
        /*#################################################
           0. MANUAL CHECK IF ROW WITH KEY(ORDER/PRODUCT) EXISTS IN DB - WORKS!
          #################################################
        Desc: Method returnes true if more than 0 rows with specified key 
        combination exists in database.
        Note: Method will not be used within the main app logic, but can be used
        to perform manual checks in DB if necessary.
        #################################################
        
        out.println("Do we have more that 0 rows in DB for (orderid=18 and productid=7): " + oDao.checkIfDuplicateRow(18, 7));
        */
        
        /*#################################################
           0. MAKE ADDTOCART BUTTON AVAILABLE TO LOGGED IN USERS - TO CODE - TO TEST
          #################################################
        Desc: TBD
        OR Make it always visible but make user login/register
        ################################################# */
        
        /*#################################################
           0. MAKE PRODUCTS DISABLED INSTEAD OF DELETED - TO CODE - TO TEST
          #################################################
        Desc: TBD
        OR copy title for Order reference
        ################################################# */
        
        


        
        
        
        
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
