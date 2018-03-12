<%-- 
    Document   : shop-checkout
    Created on : Mar 09, 2018, 16:50:10 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Checkout</h2>
            
            <div class="inner-div-full-width">
                
                <p>Enter Shipping information</p>
                <p>Enter Billing information</p>
                <p><a href='shopping-cart.jsp'>Go back to cart?</a></p>
                <p><a href='product-list.jsp'>Add some more products to cart?</a></p>
                       
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
