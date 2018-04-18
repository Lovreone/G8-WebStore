<%-- 
    Document   : template <!-- CHANGE!!!!-->
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Orders - CRUD Tests</title> <!-- CHANGE!!!!-->
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>TESTING ENVIRONMENT</h2>
 
            <h5>Testing environment</h5> 
            
            <form action="CreateOrder" method="post">
                <!-- Hidden field product id -->
                Product id (1,2,3,4,5,6,7,8,9,11,13,14):<br>
                <input type="number" name="productid" value="13<%--=p.getProductId()--%>"/>
                <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                <br>Product qty:<br>
                <input type="number" name="quantity" value="1">
                <br>
                <input type="submit" value="Add to Cart">
            </form> 
             
                
            <!-- 
            
            User creates an order (adds item to cart)
                if no orders that are 'pending' for that user
            
            User updates an order (user adds/removes an item to/from cart)
                add item
                remove item
                change item qty
                
            User completes an order (finishes purchase)
                change order.state to 'completed'
                new 'pending' order is created 
            
            Shopping cart
                displays list of items tied to a 'pending' order
                user can have only one pending order at a time
                user can have infinite completed orders
            
            Future Problems:
                Lock product modification/deletion while user purchases
                cant delete product, because of completed order history
                ...
            -->

            <div class="inner-div">

            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
