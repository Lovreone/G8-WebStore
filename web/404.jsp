<%-- 
    Document   : 404
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page not found</title> <!-- CHANGE!!!!-->
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Page not found</h2> <!-- CHANGE!!!!-->

            <div class="inner-div centered-content">
                <h1>Error 404: Requested page is not found.</h1>
                <h2>Go to <a href="index.jsp">Home page</a>?</h2>   
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
