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
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
           ADD PRODUCT TO CART (LoggedInUser ONLY)
          #################################################
        Desc: LoggedIn User enters desired quantity and clicks on AddToCart button
        on Product page. Selected product and qty is added to cart (OrderProducts table).
        Getting active Order and selected Product from DB. Creating an OrderProduct 
        object and linking it to the User and Order objects, and seting it's qty.
        Checking if selected Product already exists in Active Order for that User,
        adding it if not, and updateing it if already exists with new qty in memory 
        before writing it into database. Updating Order product in DB and with it 
        the OrderProduct(cart item) as well.
        Note: This logic tests if Hibernate mapping between Order and Product
        (ManyToMany) is implemented correctly. Logic will be used behind 
        AddToCart button(form) on Single Product page.
        Test: 1)Adding when cart is empty 2)Adding same product to cart twice 3)Adding 
        same product to cart twice while there are multiple different products in Order.
        ################################################# */
        
        PrintWriter out = response.getWriter(); // Remove redirect at the end to see the logs
        
        int qty = Integer.parseInt(request.getParameter("quantity"));
        String productId = request.getParameter("productid");
        String userId = request.getParameter("userid");
        
        OrderDao oDao = new OrderDao();
        ProductDao pDao = new ProductDao();
        
        // GET ACTIVE ORDER FOR LOGGED IN USER, GET PRODUCT THAT NEEDS TO BE ADDED TO CART
        Order order = oDao.getActiveOrder(userId); // WORKS - out.println("Active Order (GetFromDB):\n\t" + order);
        Product product = pDao.getSingleProduct(productId); // WORKS - out.println("Selected Product (GetFromDB):\n\t" + product);
        OrderProduct op = new OrderProduct(order, product, qty); // WORKS - out.println("New OrderProduct creation (InMemory):\n\t" + op);
        
        
        // CONSOLE LOG - OLD CART STATE (DB>MEMORY) + PENDING UPDATE (MEMORY)
        out.println("\n--------------------------\nCART STATE BEFORE ADDING: (DB>MEMORY)");     
        int i = 1;
        for (OrderProduct singleItem : order.getOrderProducts()) {
            out.println("\tItem " + i++ + ": " + singleItem);
        }
        out.println("PENDING UPDATE:\n\tItem x: " + op + "\n--------------------------\n");

        
        // CHECK (IN DB) IF PRODUCT ALEADY IN CART & UPDATE OR ADD TO CART
        // (CREATES NEW OR UPDATES EXISTING OrderProduct ROW IN DB) 
        Set<OrderProduct> cartItemsList = order.getOrderProducts();
        boolean itemWasAlreadyInCart = false;
        for (OrderProduct singleItem : cartItemsList) { 
            if (op.getPk().toString().equals(singleItem.getPk().toString())) {
                singleItem.setProductQty(singleItem.getProductQty() + qty);
                itemWasAlreadyInCart = true;
                out.println("LOGIC DONE: Updating existing product in cart!"); 
            }     
        }
        if (!itemWasAlreadyInCart) { 
            order.getOrderProducts().add(op); // Doesn't work with LAZY but works with EAGER - Order.java:61
            out.println("LOGIC DONE: Adding a new product to cart!");
        }
        
        
        // CONSOLE LOG - NEW CART STATE (MEMORY)
        out.println("\n--------------------------\nNEW CART STATE: (MEMORY)");     
        int j = 1;
        for (OrderProduct singleItem : order.getOrderProducts()) {
            out.println("\tItem " + j++ + ": " + singleItem);
        }
        out.println("--------------------------\n");

        
        // MAIN APPROACH - MKYONG WAY (WORKING WITH ORDER ENTITY)
        String status = oDao.updateOrder(order); // UPDATES
        out.println("Attempting to update Order in DB: " + status);  
        
        // ALTERNATE APPROACH (WORKING WITH OP ITEMS) - NOT USED, BUT WORKS   
        // It works directly with OP and is not what mkyong's tutorial intended
            // String status = oDao.addToCart(op); // SAVES
            // String status = oDao.mergeObjectTest(op); // MERGES
        
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
