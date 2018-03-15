/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

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
        
        // Shipping information
        String fName = request.getParameter("first_name");
        String lName = request.getParameter("last_name");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String streetAddress = request.getParameter("street_address");
        
        // Billing information
        String cardType = request.getParameter("cc_type");
        String ccNumber = request.getParameter("cc_number");
        String expDateMonth = request.getParameter("exp_month");
        String expDateYear = request.getParameter("exp_year");
        String cardHolderName = request.getParameter("card_holder_name");
        String cvvNumber = request.getParameter("cvv_number");
                
                
        PrintWriter out = response.getWriter();
        
        out.println("\nSHIPPING INFO:\n----------------------------\n");
        out.println(fName + "\n" + lName + "\n" + city + "\n" + country + "\n" + streetAddress + "\n");
        out.println("\nBILLING INFO:\n----------------------------\n");
        out.println(cardType + "\n" + ccNumber + "\n" + expDateMonth + "\n" + expDateYear + "\n" + cardHolderName + "\n" + cvvNumber + "\n");
       
        
        
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
