/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.UserDao;
import com.app.domains.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lovreone
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        response.sendRedirect("login.jsp"); // if user goes directly for /G8-Webstore/Login
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao ud;
        
        if ((email != null && !email.isEmpty()) && (password != null && !password.isEmpty())) {
            ud = new UserDao();
        
            List<User> users = ud.loginCheck(email, password);
            User u = !users.isEmpty() ? users.get(0) : null;
            
            if ( u != null) {
                HttpSession session = request.getSession();
                
                session.setAttribute("userid", u.getUserId());
                session.setAttribute("email", u.getEmail());
                session.setAttribute("isadmin", u.isIsAdmin());
                session.setAttribute("fname", u.getUserDetails().getFirstName());
                session.setAttribute("lname", u.getUserDetails().getLastName());
                
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("errormessage", "Wrong credentials!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        } else {
            request.setAttribute("errormessage", "Fill in all mandatory fields(*)!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
