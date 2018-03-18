<%-- 
    Document   : cms-product-manage
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="java.util.List"%>
<%@page import="com.app.domains.*"%>
<%@page import="com.app.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CMS: Manage Products</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>CMS: Manage Products</h2> 
            <div class="inner-div">

                <div class="col-md-12 pretty-form-bckg">    
                    <c:choose>
                        <c:when test="${sessionScope.userid != null && sessionScope.isadmin == true}"> <%-- ADMIN ONLY --%>

                            <ul class="nav nav-tabs padding-top">
                                <li role="presentation" class="active"><a href="#">Manage all products</a></li>
                                <li role="presentation"><a href="cms-product-create.jsp">Add a new product</a></li> 
                            </ul>

                            <table class="table table-striped table-responsive padding-top">
                                <tr>
                                    <th>ID</th>
                                    <th>Model</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>ImagePath</th>
                                    <th></th>
                                </tr>  
                                <%
                                    ProductDao pd = new ProductDao();
                                    List<Product> productsList = pd.getAllProducts();
                                    for (Product product : productsList) {
                                %>
                                <tr>
                                    <td class="vertical-center"><%=product.getProductId()%></td>
                                    <td class="vertical-center"><a href="product-single.jsp?id=<%=product.getProductId()%>" target="blank"><%=product.getProductName()%></a></td>
                                    <td class="vertical-center"><%=product.getUnitPrice()%></td>
                                    <td class="vertical-center"><%=product.getStockQuantity()%></td>
                                    <td class="vertical-center"><%=product.getProductDetails().getImagePath()%></td>
                                    <td class="vertical-center">
                                        <form action="ManageProducts" method="post">
                                            <%--a href="product-single.jsp?id=<%=product.getProductId()%>" target="blank" class="btn btn-default" role="button">
                                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"/>
                                            </a--%>  
                                            <input type="hidden" name="productid" value="<%=product.getProductId()%>"/>
                                            <button type="submit" class="btn btn-primary btn-sm" name="buttonaction" value="edit">
                                                <span class="glyphicon glyphicon-cog no-padding" aria-hidden="true"/>
                                            </button>
                                            <button type="submit" class="btn btn-primary btn-sm" name="buttonaction" value="delete" onClick="return areYouSure()">
                                                <span class="glyphicon glyphicon-trash no-padding" aria-hidden="true"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                    if (productsList.isEmpty()) {
                                        out.print("<div class='centered-content'><h3>No products found in database.</h3></div>");
                                    }
                                %>
                            </table>

                            <script>
                                function areYouSure() {
                                    if (!confirm('You are about to permanently delete this product from database!'))
                                        return false;
                                }
                            </script>

                        </c:when><%-- /ADMIN ONLY --%>
                        <c:when test="${sessionScope.userid != null && sessionScope.isadmin == false}"><%-- USER ONLY --%>
                            <div class="inner-div centered-content">
                                <h1>Restricted page</h1>
                                <h3>You do not have permissions to access this page!</h3> 
                                <h3>Go to your <a href="dashboard.jsp">Dashboard</a>.</h3>
                            </div>
                        </c:when> <%-- /USER ONLY --%>       
                        <c:when test="${sessionScope.userid == null}"> <%-- LOGGED OUT --%>
                            <div class="inner-div centered-content">
                                <h1>Restricted page</h1>
                                <h3>You must be logged in in order to access this page!</h3> 
                                <h3>Click here to <a href="login.jsp">login</a>.</h3>
                            </div>
                        </c:when> <%-- /LOGGED OUT --%>
                        <c:otherwise><%-- ELSE --%>
                            <div class="inner-div centered-content">
                                <h1>Restricted page</h1>
                                <h3>Oops... Something went wrong!</h3> 
                                <h3>Go back to <a href="index.jsp">Home page</a>.</h3>
                            </div>
                        </c:otherwise><%-- /ELSE --%>    
                    </c:choose>     
                </div>

            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
