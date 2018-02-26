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
           1. CREATE A NEW ORDER MANUALLY - TEST (Must be Logged in!) - WORKS!
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
           2. & 3. ADD PRODUCT TO CART - TEST (LoggedInUser HardCoded) - WORKS!
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
        Test Adding if: 1)Cart is Empty 2)Adding same product to cart twice 3)Adding 
        same product to cart twice while there are multiple different products in Order.
        ################################################# 
        
        // ADDS PRODUCT TO CART (CREATES NEW OD ROW IN DB) 
        Order order = oDao.getActiveOrder("10"); // WORKS - 
            out.println("Active Order (GetFromDB):\n\t" + order);
        Product product = pDao.getSingleProduct(String.valueOf(productId)); // WORKS - 
            out.println("Selected Product (GetFromDB):\n\t" + product);
        OrderProduct op = new OrderProduct(order, product, qty); // WORKS - 
            out.println("New OrderProduct creation (InMemory):\n\t" + op);
        
        // CONSOLE LOG - OLD CART STATE + PENDING UPDATE
        out.println("\n-------------------------------------------------------------------\nCART STATE BEFORE ADDING: (DB>MEMORY)");     
        int i = 1;
        for (OrderProduct singleItem : order.getOrderProducts()) {
            out.println("\tItem " + i++ + ": " + singleItem);
        }
        out.println("PENDING UPDATE:\n\tItem x: " + op + "\n-------------------------------------------------------------------\n");

        // CHECK IF PRODUCT ALEADY IN CART & UPDATE OR ADD TO CART
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
        out.println("\n-------------------------------------------------------------------\nNEW CART STATE: (MEMORY)");     
        int j = 1;
        for (OrderProduct singleItem : order.getOrderProducts()) {
            out.println("\tItem " + j++ + ": " + singleItem);
        }
        out.println("-------------------------------------------------------------------\n");

        // MAIN APPROACH - MKYONG WAY (WORKING WITH ORDER)
        String status = oDao.updateOrder(order); // UPDATES
        out.println("Attempting to save Order to DB: " + status);  
        
        // ALTERNATE APPROACH - NOT MKYONG WAY (WORKING WITH OP ITEMS) - NOT USED, BUT WORKS   
            // This approach works directly with OP and is not what mkyong's tutorial intended
            // String status = oDao.addToCart(op); // SAVES
            // String status = oDao.mergeObjectTest(op); // MERGES
        */
        
        
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
                + "Which Order is updated in DB as 'completed'?\n\t" + order.toString() + "\n" // + oDao.getSingleOrder(order.getOrderId()) - java.lang.StackOverflowError
                + "Order's Details saved in DB are:\n\t" + od.toString() + "\n" // + oDao.getSingleOrder(order.getOrderId()).getOrderDetails() - java.lang.StackOverflowError
        );
        od = null;
        order = new Order("pending", 0, "00.00.0000 00:00");
        User u = uDao.getSingleUser(userId);  
        order.setUser(u);
        boolean status = oDao.createOrder(order);
        out.println("------------------------\nCREATE ORDER - CHANGES:\n------------------------\n"
                + "Is new 'pending' Order creation for this User successful?\n\t" + status + "\n"
                + "New 'pending' Order created in DB:\n\t" + order.toString() + "\n"
        ); 
        */
                
        
        
        /*#################################################
           MANUAL CHECK IF ROW WITH KEY(ORDER/PRODUCT) EXISTS IN DB - WORKS!
          #################################################
        Desc: Method returnes true if more than 0 rows with specified key 
        combination exists in database.
        Note: Method will not be used within the main app logic, but can be used
        to perform manual checks in DB if necessary.
        #################################################
        
        out.println("Do we have more that 0 rows in DB for (orderid=18 and productid=7): " + oDao.checkIfDuplicateRow(18, 7));
        */
        
        


        
        
        
        
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
