<%-- 
    Document   : store-success
    Created on : Mar 22, 2018, 01:10:39 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Purchase complete</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Purchase complete!</h2>

            <div class="inner-div centered-content">
                <h3>Your order has been successfully completed!</h3>
                <h4>View <a href="#">previous orders</a>?</h4> 
                <h4>Browse <a href="product-list.jsp">more products</a>?</h4>   
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
