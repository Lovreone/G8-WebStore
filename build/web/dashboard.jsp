<%-- 
    Document   : dashboard
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User Dashboard</title>
        <%@include file="head-meta.jsp"%>   
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        
        <div class="container stylish-div-background">
            <h2>User Dashboard</h2>

            <div class="inner-div">
                
            <c:if test="${sessionScope.userid != null}"> <!-- LOGGED IN -->
                
                <h4>Welcome, <b>${sessionScope.fname} ${sessionScope.lname}</b> | <a href="Logout">Log out</a></h4>   
                
                <c:if test="${sessionScope.userid != null && sessionScope.isadmin == false}"> <!-- USER ONLY-->
                    <h2>User Profile section</h2>
                    <p>Email address used for registration: <b>${sessionScope.email}</b></p>     
                    <p>Dashboard Quicklinks: <b>COMING SOON!</b></p>
                    <ul>
                        <li><a href="product-list.jsp">Explore our latest products</a></li>
                        <li><a href="shopping-cart.jsp">My Shopping cart</a></li>
                        <li><a href="#">Completed Orders</a></li>
                        <li><a href="#">Edit profile information</a></li>     
                    </ul>    
                </c:if><!-- /USER ONLY-->     
                
                <c:if test="${sessionScope.userid != null && sessionScope.isadmin == true}"> <!-- ADMIN ONLY-->
                    <h2>Admin section</h2>
                    <p>Content Management Quicklinks:</p>
                    <ul>
                        <li><a href="cms-product-manage.jsp" target="blank">CMS: Manage existing Products</a></li>
                        <li><a href="cms-product-create.jsp" target="blank">CMS: Create a new Product</a></li>
                    </ul>    
                </c:if><!-- /ADMIN ONLY -->
                
            </c:if> <!-- /LOGGED IN -->

            <c:if test="${sessionScope.userid == null}"> <!-- LOGGED OUT -->
                <div class="inner-div" style="text-align: center; vertical-align: middle;">
                    <h1>Restricted page</h1>
                    <h3>You must be logged in in order to access this page!</h3> 
                    <h3>Click here to <a href="login.jsp">login</a>.</h3>
                </div>
            </c:if> <!-- /LOGGED OUT -->
                  
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
