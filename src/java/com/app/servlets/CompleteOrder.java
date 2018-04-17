/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.OrderDao;
import com.app.dao.UserDao;
import com.app.domains.Order;
import com.app.domains.OrderDetails;
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
@WebServlet(name = "CompleteOrder", urlPatterns = {"/CompleteOrder"})
public class CompleteOrder extends HttpServlet {

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
            out.println("<title>Servlet CompleteOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompleteOrder at " + request.getContextPath() + "</h1>");
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
        
        /*#################################################
           COMPLETE ACTIVE ORDER > CREATE NEW PENDING ORDER (LoggedInUser ONLY)
          #################################################
        Desc: Getting an existing active Order from DB by logged in User's id. 
        Method should return the "pending" Order for that User, which should be 
        only 1 result. Creation of OrderDetails object (needed for Order completion), 
        linking Order with OrderDetails, updating status to "completed". Updating 
        exising Order will both Update status and create a new OrderDetails linked
        to it. After existing Order is "completed" we create a new "pending" Order
        for that User.
        Note: This logic tests if Hibernate mapping between Order and 
        OrderDetails (OneToOne) is implemented correctly. This logic will be 
        used at the end of the shopping proces when a User completes an Order.
        ################################################# */
        
        PrintWriter out = response.getWriter(); // Remove redirect at the end to see the logs
        
        String userId = request.getParameter("userid");
        String buttonAction = request.getParameter("buttonaction");
                
        /*
        User comes from Shipping page - form submission (shippinginfo):
            get shipping data from form
            if 0 will have to save partial data
            if 1 will have to update partial data
            redirect to billing page
                      
        User comes from Billing page - form submission (completepurchase):
            get billing data from form
            completes current pending order
            creates new pending order
            redirect to success page
        */
        
        
        // User comes from Shipping page (store-shipping.jsp) 
        if (buttonAction.equals("shippinginfo")) {
            
            // Get Shipping information params
            String fName = request.getParameter("first_name");
            String lName = request.getParameter("last_name");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String streetAddress = request.getParameter("street_address");
            
            // TEMP CONSOLE LOG WITH PARAMETERS - WORKS!
            out.println("\nSHIPPING INFO:\n----------------------------\n");
            out.println(fName + "\n" + lName + "\n" + city + "\n" + country + "\n" + streetAddress + "\n");
            
            out.println("LOGIC DONE: Saving/Updating Shipping info for order!");
            response.sendRedirect("store-billing.jsp");
            
        // User comes from Billing page (store-billing.jsp) 
        } else if (buttonAction.equals("completepurchase")) {

            // Get Billing information params
            String cardType = request.getParameter("cc_type");
            String ccNumber = request.getParameter("cc_number");
            String expDateMonth = request.getParameter("exp_month");
            String expDateYear = request.getParameter("exp_year");
            String cardHolderName = request.getParameter("card_holder_name");
            String cvvNumber = request.getParameter("cvv_number");
            
            // TEMP CONSOLE LOG WITH PARAMETERS - WORKS!
            out.println("\nBILLING INFO:\n----------------------------\n");
            out.println(cardType + "\n" + ccNumber + "\n" + expDateMonth + "\n" + expDateYear + "\n" + cardHolderName + "\n" + cvvNumber + "\n");
            
            out.println("LOGIC DONE: Saving Payment info, completing order, creating new order !");
            response.sendRedirect("store-success.jsp");
            
        } else {

            out.print("NEITHER BRANCH: An Error has occured!!!");
        }
        
              
        

       
        /*
        OrderDao oDao = new OrderDao();
        UserDao uDao = new UserDao();
        
        Order order = oDao.getActiveOrder(userId);
        OrderDetails od = new OrderDetails("Pera", "Peric", "Beograd", "Serbia", "Petra Lekica 3/2", "Visa", "1234567890123456", 12, 2019, "Pera Peric", 123);
        order.setOrderDetails(od);
        od.setOrder(order);
        order.setStatus("completed");
        oDao.updateOrder(order);
        
        out.println("\n------------------------\nUPDATE EXISTING 'PENDING' ORDER - CHANGES:\n------------------------\n"
                + "Which Order is updated in DB as 'completed'?\n\t" + order.toString() + "\n" // + oDao.getSingleOrder(order.getOrderId()) - java.lang.StackOverflowError
                + "Order's Details saved in DB are:\n\t" + od.toString() + "\n" // + oDao.getSingleOrder(order.getOrderId()).getOrderDetails() - java.lang.StackOverflowError
        );
        od = null;
        
        
        order = new Order("pending", 0, "00.00.0000 00:00");
        User u = uDao.getSingleUser(userId);  
        order.setUser(u);
        boolean status = oDao.createOrder(order);
       
        out.println("------------------------\nCREATE A NEW 'PENDING' ORDER - CHANGES:\n------------------------\n"
                + "Is new 'pending' Order creation for this User successful?\n\t" + status + "\n"
                + "New 'pending' Order created in DB:\n\t" + order.toString() + "\n"
        ); 
        */
        
        // response.sendRedirect("store-success.jsp");
        
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
