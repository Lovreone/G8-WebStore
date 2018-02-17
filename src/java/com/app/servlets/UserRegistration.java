/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.OrderDao;
import com.app.dao.UserDao;
import com.app.domains.Order;
import com.app.domains.User;
import com.app.domains.UserDetails;
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
@WebServlet(name = "UserRegistration", urlPatterns = {"/UserRegistration"})
public class UserRegistration extends HttpServlet {

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
            out.println("<title>Servlet UserRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRegistration at " + request.getContextPath() + "</h1>");
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
        
        String fName = request.getParameter("firstname");
        String lName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");
        
        // MANDATORY FIELD CHECK > USER REGISTRATION
        if ((fName != null && !fName.isEmpty()) && (lName != null && !lName.isEmpty())
            && (email != null && !email.isEmpty()) && (pass1 != null && !pass1.isEmpty()) 
            && (pass2 != null && !pass2.isEmpty()) && (pass1.equals(pass2))) {
                        
            UserDao daoObj = new UserDao();
            User u = new User(email, pass1);
            UserDetails ud = new  UserDetails(fName, lName);

            u.setUserDetails(ud);
            ud.setUser(u);

            boolean doesEmailExist = daoObj.isExistingUser(email);
            
            // EMAIL EXISTS IN DB CHECK
            if (doesEmailExist) {
                request.setAttribute("statusmessage", "Email: '" + email + "' already exitsts in database!");
                request.getRequestDispatcher("/register.jsp").forward(request, response);  
                
            // REGISTRATION SUCCESS / FAIL MESSAGES 
            } else {
                int newUserId = daoObj.createUser(u);
                // ########## NEW CODE BELOW - CREATE 'pending' ORDER ########### - WORKS
                    OrderDao oDao = new OrderDao();
                    Order o = new Order("pending", 0, "DD.MM.YYYY HH:MM");
                    o.setUser(u);
                    boolean regStatus = oDao.createOrder(o);
                    
                if (newUserId > 0) {
                    request.setAttribute("registersuccess", "User '" + fName + " " + lName + "' has successfully registered!");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else {
                    request.setAttribute("statusmessage", "Oops, Something went wrong. Please try again.");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);  
                }  
            }
        
        // EMPTY FIELD AND PASS MISSMACH VALIDATION    
        } else {
            request.setAttribute("statusmessage", "Please fill in all fields marked with *!"); 
            if (pass1 != null && pass2 != null && !pass1.isEmpty() && !pass2.isEmpty()) {
                if (!pass1.equals(pass2)) request.setAttribute("passmismatch", "Password Missmatch! ");   
            } 
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }   
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
