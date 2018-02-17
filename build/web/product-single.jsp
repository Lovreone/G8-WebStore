<%-- 
    Document   : product-single
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="com.app.dao.ProductDao"%>
<%@page import="com.app.domains.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Product page</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Product page</h2>
            
            <div class="inner-div">
                
                <% String id = request.getParameter("id"); %>
                <c:if test="${param.id != null && !param.id.isEmpty()}">
                    
                    <% 
                        ProductDao pd = new ProductDao();
                        Product p = pd.getSingleProduct(id); 
                        // Problem with below check using JSTL, can't reach object 
                        if (p != null) { 
                    %>
                           
                    <div class="container">
                        <div class="row justify-content-start">
                            <div class="col-5 col-sm-5 col-md-5">
                                <img src="<%=p.getProductDetails().getImagePath()%>" class="img-responsive img-thumbnail" alt="<%=p.getProductName()%>"/>
                            </div>
                            <div class="col-5 col-sm-5 col-md-5" style="text-align: left">
                                <h3><b>Model: </b><%=p.getProductName()%></h3>
                                <h4><b>Price: </b><%=p.getUnitPrice()%> &euro;</h4>
                                <h4><b>Stock: </b><%=p.getStockQuantity()%></h4>
                                <h4><b>Description: </b></h4><p><%=p.getProductDetails().getDescription()%></p>
                            </div>
                        </div>  
                    </div>          
                    <hr>        
                    <%        
                        } else {
                    %>
                        <h4>Oops! Something went wrong!</h4>
                    <% 
                        }
                    %>
                </c:if>
                <h4>Go back to <a href="product-list.jsp">product list</a> page</h4>
            
            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
