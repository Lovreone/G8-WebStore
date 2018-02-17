/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servlets;

import com.app.dao.ProductDao;
import com.app.domains.Product;
import com.app.domains.ProductDetails;
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
@WebServlet(name = "ManageProducts", urlPatterns = {"/ManageProducts"})
public class ManageProducts extends HttpServlet {

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
            out.println("<title>Servlet ManageProducts</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageProducts at " + request.getContextPath() + "</h1>");
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
        
        String buttonAction = request.getParameter("buttonaction");
        String productId = request.getParameter("productid");
        
        ProductDao pDao; 
        
        // User comes from Product Manage page-Delete (cms-product-manage.jsp) 
        if (buttonAction.equals("delete")) {
            pDao = new ProductDao();
            pDao.deleteProduct(productId);
            request.getRequestDispatcher("/cms-product-manage.jsp").forward(request, response);
        
        // User comes from Product Manage page-Edit (cms-product-manage.jsp) 
        } else if (buttonAction.equals("edit")) {
            request.setAttribute("productid", productId);
            request.getRequestDispatcher("/cms-product-edit.jsp").forward(request, response);
            
        // User comes from Edit product page (cms-product-edit.jsp)   
        } else if (buttonAction.equals("modify")) { 
            String productName = request.getParameter("productname");
            String unitPrice = request.getParameter("unitprice");
            String qtyInStock = request.getParameter("stockqty");
            String productDesc = request.getParameter("description");
            String imgPath = request.getParameter("imagepath");
            
            if ((productName != null && !productName.isEmpty()) && (unitPrice != null && !unitPrice.isEmpty())
            && (qtyInStock != null && !qtyInStock.isEmpty()) && (productDesc != null && !productDesc.isEmpty()) 
            && (imgPath != null && !imgPath.isEmpty())) { // 
            
                pDao = new ProductDao();

                Product p = new Product();
                p.setProductId(Integer.parseInt(productId));
                p.setProductName(productName);
                p.setUnitPrice(Integer.parseInt(unitPrice));
                p.setStockQuantity(Integer.parseInt(qtyInStock));

                ProductDetails pd = new ProductDetails();
                pd.setDescription(productDesc);
                pd.setImagePath(imgPath);

                p.setProductDetails(pd);
                pd.setProduct(p);
                pDao.updateProduct(p);
                
                request.setAttribute("successmessage", "Success! Product: '" + productName + "' data is changed!");
            
            } else {
                request.setAttribute("errormessage", "Please fill in all fields marked with *!");
            }
            
            request.getRequestDispatcher("/cms-product-edit.jsp").forward(request, response);  
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
