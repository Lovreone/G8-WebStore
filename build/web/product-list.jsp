<%-- 
    Document   : product-list
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="com.app.domains.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.app.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Products</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Product list</h2>

            <div class="inner-div">

                <!-- https://getbootstrap.com/docs/3.3/components/#thumbnails-custom-content -->       
                <div class="row">       
                <%
                    ProductDao pd = new ProductDao();
                    List<Product> productsList = pd.getAllProducts();           
                    for (Product product : productsList) {         
                %>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="<%=product.getProductDetails().getImagePath()%>" alt="<%=product.getProductName()%>" class="img-thumbnail"> <!-- images/charlie-chaplin.jpg --> <!-- Try class="img-rounded" -->
                            <div class="caption">
                                <h5><b><%=product.getProductName()%></b></h5>
                                <h5><b>Price:</b> <%=product.getUnitPrice()%> &euro;</h5>
                                <p><a href="product-single.jsp?id=<%=product.getProductId()%>" class="btn btn-primary" role="button">Details</a></p>
                            </div>
                        </div>
                    </div>  
                <%
                    }
                %>
                </div> 
                <% if (productsList.isEmpty()) out.print("<h3 style='text-align: center;'>No products found in database.</h3>"); %>

            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
