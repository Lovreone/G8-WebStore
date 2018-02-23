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
                + " LoggedUserID: " + (!userId.isEmpty() ? userId : "NOT LOGGED IN!"));
        
        out.println("\n###############################################################\n");
                
        // GENERAL ITEMS USED FOR DATABASE OPERATIONS
        OrderDao oDao = new OrderDao();
        UserDao uDao = new UserDao();
        ProductDao pDao = new ProductDao();
        
        /*#################################################
           CREATE A NEW ORDER MANUALLY - TEST (Must be Logged in!) - WORKS!
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
           6. COMPLETE ACTIVE ORDER > CREATE NEW PENDING ORDER - TEST (Must be Logged in!) - WORKS!
          #################################################
        Desc: Getting an existing active Order from DB by logged in User's id. Method should
        return the "pending" Order for that User, which should be only 1 result.
        Creation of OrderDetails object (needed for Order completion), linking Order
        with OrderDetails, updating status to "completed". Updating exising Order
        will both Update status and create a new OrderDetails linked to it. After
        existing Order is "completed" we create a new "pending" Order for that User.
        Note: This logic tests if Hibernate mapping between Order and OrderDetails
        (OneToOne) is implemented correctly. This logic will be used at the end 
        of the shopping proces when a User completes an order.
        #################################################
        
        Order order = oDao.getActiveOrder(userId);
        OrderDetails od = new OrderDetails("Pera", "Peric", "Beograd", "Serbia", "Petra Lekica 3/2", "Visa", "1234567890123456", 12, 2019, "Pera Peric", 123);
        order.setOrderDetails(od);
        od.setOrder(order);
        order.setStatus("completed");
        oDao.updateOrder(order);
        out.println("------------------------\nUPDATE ORDER - CHANGES:\n------------------------\n"
                + "Which Order is updated in DB as 'completed'?\n\t" + oDao.getSingleOrder(String.valueOf(order.getOrderId())).toString() + "\n"
                + "Order's Details saved in DB are:\n\t" + oDao.getSingleOrder(String.valueOf(order.getOrderId())).getOrderDetails().toString() + "\n"
        );
        od = null;
        order = new Order("pending", 0, "00.00.0000 00:00");
        User u = uDao.getSingleUser(userId);  
        order.setUser(u);
        boolean status = oDao.createOrder(order);
        out.println("------------------------\nCREATE ORDER - CHANGES:\n------------------------\n"
                + "Is new 'pending' Order creation for this User successful?\n\t" + status + "\n"
                + "New 'pending' Order created in DB:\n\t" + order.toString() + "\n"
        ); */
        
       
        
        
        
        /*#################################################
           ADD PRODUCT TO CART - TEST (Must be Logged in!)
          #################################################
        Desc: LoggedIn User clicks on AddToCart button on Product page. Selected 
        product with qty of 1(default) is added to cart (OrderProducts table).
        
        Note: This logic tests if Hibernate mapping between Order and Product
        (ManyToMany) is implemented correctly. Logic will be used behind 
        AddToCart button(form) on Single Product page.
        ################################################# */
        
        
              
        // ADDS PRODUCT TO CART (CREATES NEW OD ROW IN DB) 
        Order order = oDao.getActiveOrder("9"); // WORKS - 
            out.println("Active Order (GetFromDB):\n\t" + order);
        Product product = pDao.getSingleProduct("13"); // WORKS - 
            out.println("Selected Product (GetFromDB):\n\t" + product);
        OrderProduct op = new OrderProduct(order, product, qty); // WORKS - 
            out.println("New OrderProduct creation (InMemory):\n\t" + op);
        
            
        out.println("\nBEFORE: order.getOrderProducts():\n"
                + "\tOld qty:\t" + order.getOrderProducts() + "\n"
                + "\tNew qty:\t" + qty + "\n"
        
        );    
        Set<OrderProduct> orderProducts = order.getOrderProducts();
        for (OrderProduct singleOP : orderProducts) {
            if (op.getPk().toString().equals(singleOP.getPk().toString())) {
                singleOP.setProductQty(singleOP.getProductQty() + qty);
                out.println("OBJECTS HAVE SAME PK VALUES - MERGING!");
            } else {
                out.println("OBJECTS DONT HAVE SAM PK VALUES - ADDING!");
                order.getOrderProducts().add(op); // Doesn't work with LAZY but works with EAGER - Order.java:61
            }
        }

        out.println("\nAFTER: order.getOrderProducts():\n\t" + order.getOrderProducts());
          
        // EVERYTHING ABOVE THIS LINE IS STABLE AND WORKS
        
    //String status = oDao.updateOrder(order); // UPDATES
        // String status = oDao.addToCart(op); // SAVES
        // String status = oDao.mergeObjectTest(op); // MERGES
        //out.println("result: " + status);

        
        
        
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
