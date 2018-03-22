/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.OrderDao;
import com.app.dao.ProductDao;
import com.app.domains.Order;
import com.app.domains.OrderProduct;
import com.app.domains.Product;
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
@WebServlet(name = "ManageCart", urlPatterns = {"/ManageCart"})
public class ManageCart extends HttpServlet {

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
            out.println("<title>Servlet ManageCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageCart at " + request.getContextPath() + "</h1>");
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
           CHANGE ITEM IN CART (UPDATE QTY/DELETE FROM CART)
          #################################################
        Desc: LoggedIn User is on Cart page and sees a list of items in cart (if
        not empty). Each cart item will have two forms, one for quantity update and 
        other for item deletion. User will input changed qty and submit changes, 
        after which servlet will update item qty and redirect back to Cart page.
        If user clicks on Delete button, and confirms with JS Y/N popup, servlet 
        will delete item form cart and take user pack to cart page.
        Note: This proves Hibernate mapping ManytToMany is implemented correctly
        To Test: All cart operations - Add, Edit, Delete
        ################################################# */
        
        PrintWriter out = response.getWriter(); // Remove redirect at the end to see the logs
        
        String userId = request.getParameter("userid");
        String buttonAction = request.getParameter("buttonaction");
        String productIdKey = request.getParameter("productid");
        String quantity = request.getParameter("quantity");
        
        OrderDao oDao = new OrderDao();
        ProductDao pDao = new ProductDao();
        
        Order order = oDao.getActiveOrder(userId); // WORKS - out.println("\norder:\n" + order);
        Product product = pDao.getSingleProduct(productIdKey); // WORKS - out.println("\nproduct:\n" + product);
        OrderProduct selectedCartItem = new OrderProduct(order, product, Integer.parseInt(quantity)); // WORKS - out.println("\ndeletedItem:\n" + selectedCartItem);
        Set<OrderProduct> cartItemsList;
        
        out.println("\nCart state BEFORE(Memory):\n"); // USED FOR STATUS LOG
        for (OrderProduct item : order.getOrderProducts()) { 
            out.println(item + "\n");
        }

        out.println("\nATTEMPTING TO CHANGE '" + selectedCartItem + "'\n");
        cartItemsList = order.getOrderProducts(); 
        for (Iterator<OrderProduct> iterator = cartItemsList.iterator(); iterator.hasNext();) { 
            OrderProduct op =  iterator.next();
            if (op.getPk().toString().equals(selectedCartItem.getPk().toString())) {

                // User comes from Cart page-Delete (shopping-cart.jsp) 
                if (buttonAction.equals("delete")) {
                    iterator.remove(); // WORKS - Added orphanRemoval=true to Order.java for this change to be saved in DB too
                    out.println("LOGIC DONE: Deleting product from cart!");

                // User comes from Cart page-Edit (shopping-cart.jsp) 
                } else if (buttonAction.equals("update")) {
                    op.setProductQty(Integer.parseInt(quantity));
                    out.println("LOGIC DONE: Updating existing product in cart!");

                } else {
                    out.print("NEITHER DELETE NOR UPDATE BRANCH: An Error has occured!!!");
                }
            }       
        }

        out.println("\nCART STATE AFTER(MEMORY):\n"); // USED FOR STATUS LOG
        for (OrderProduct item : order.getOrderProducts()) { 
            out.println(item + "\n");
        }

        String status = oDao.updateOrder(order);
        out.println("\nATTEMPTING TO SAVE CHANGES TO DB: " + status);
            
        response.sendRedirect("store-cart.jsp");
    
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
