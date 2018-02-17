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
        // FE TESTS: http://localhost:8080/G8-WebStore/dashboard.jsp
        int qty = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("productid"));
        String userId = request.getParameter("userid");
        
        PrintWriter out = response.getWriter();
        out.println("STATUS:\n qty: " + qty + ",\n productID: " + productId + ",\n userID: " + (!userId.isEmpty() ? userId : "NOT LOGGED IN!") + "\n");
        
        
               

        OrderDao oDao = new OrderDao();
        //Order o = new Order("pending", 0, "00.00.0000 00:00");
        /*UserDao uDao = new UserDao();
        User u;      
        if (!userId.isEmpty()) {
            u = uDao.getSingleUser(userId);  
            o.setUser(u);
        }
        */
        /*boolean regStatus = oDao.createOrder(o);
        out.println("Create Success? " + regStatus);*/
        
        
        
        
        
        
        
        /*
        o = oDao.getSingleOrder(userId); // Method should return 'pending' order for that user ID, which should be only 1 result
        OrderDetails od = new OrderDetails("Pera", "Peric", "Beograd", "Serbia", "Petra Lekica 3/2", "Visa", "1234567890123456", 12, 2019, "Pera Peric", 123);
        o.setOrderDetails(od);
        od.setOrder(o);
        o.setStatus("completed");
        oDao.updateOrder(o); // update metoda da update-uje postojeci o, a kreira za njega novi od
        o = new Order("pending", 0, "00.00.0000 00:00");
        od = null;
        boolean regStatus = oDao.createOrder(o);
        */
        

       
        
        
        // znamo usera (get user), znamo order (get order)
        // znamo proizvod > dodajemo proizvod u order
            // oduzimamo proizvod iz ordera
        // listamo order da vidimo promene
        
        // ADDS PRODUCT TO CART (CREATES NEW OD ROW IN DB)
        ProductDao pDao = new ProductDao();
        Order order = oDao.getSpecificOrder("8", "pending"); // WORKS - out.println(order.toString());
        Product product = pDao.getSingleProduct("13"); // WORKS - out.println(product.toString());
        OrderProduct op = new OrderProduct();
            op.setOrder(order);
            op.setProduct(product);
            op.setProductQty(qty);
        // String result = oDao.addToCart(op); // SAVES
        String result = oDao.mergeObjectTest(op); // MERGES
        out.println("result: " + result);
        out.println("OP: " + op);
        
        
        
        
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
