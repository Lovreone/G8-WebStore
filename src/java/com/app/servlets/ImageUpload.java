/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author grupa 1
 */
@WebServlet(name = "ImageUpload", urlPatterns = {"/ImageUpload"})
@MultipartConfig
public class ImageUpload extends HttpServlet {

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
        
        response.sendRedirect("gallery_upload.jsp");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImageUpload</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImageUpload at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");  
        }
        
        //response.sendRedirect(request.getHeader("Referer"));
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
        
        String productId = request.getParameter("productid");
        String buttonAction = request.getParameter("buttonaction");
        
        try {
            Part filePart = request.getPart("picture");
            String contentDisp = filePart.getHeader("content-disposition");
            String fileName = contentDisp.split("filename=\"")[1].replace("\"", "");
            InputStream is =  filePart.getInputStream();
            // Below path works only on Windows 7 (any user), needs to be changed depending on live server machine OS
            FileOutputStream fos = new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\NetBeansProjects\\G8-WebStore\\web\\images\\products\\" + fileName);
            int bt;
            while ((bt = is.read()) != -1) {
                fos.write(bt);
            }
            fos.close();
            is.close();
            String imagePath = "images/products/" + fileName;
            request.setAttribute("imgpath", imagePath);
            request.setAttribute("uploadsuccess", "File '" + fileName + "' has been successfully uploaded to server, submit form to set it as product image");
        } catch (FileNotFoundException ex) { 
            request.setAttribute("uploaderror", "Nothing to upload, please select file");  
        } finally {  
            
            // user comes from cms-product-create.jsp
            if (buttonAction.equals("create")) {
                request.getRequestDispatcher("cms-product-create.jsp").forward(request, response); 
            
            // user comes from cms-product-edit.jsp
            } else if (buttonAction.equals("edit")){
                request.setAttribute("productid", productId);
                request.getRequestDispatcher("cms-product-edit.jsp").forward(request, response); 
            }  
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
