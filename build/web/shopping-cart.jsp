<%-- 
    Document   : shopping-cart
    Created on : Mar 01, 2018, 16:40:20 PM
    Author     : Lovreone
--%>

<%@page import="com.app.domains.Product"%>
<%@page import="com.app.domains.Order"%>
<%@page import="java.util.Set"%>
<%@page import="com.app.domains.OrderProduct"%>
<%@page import="com.app.dao.OrderDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping cart</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Shopping cart</h2>

            <c:choose> 
                <c:when test="${sessionScope.userid != null && sessionScope.isadmin == false}"><%-- USER ONLY --%>
                <div class="row">  
                    <ul>
                    <%
                        OrderDao od = new OrderDao();
                        Order order = od.getActiveOrder(String.valueOf(session.getAttribute("userid")));
                        Set<OrderProduct> cartItems = order.getOrderProducts();
                        int totalPrice = 0;
                        for (OrderProduct cartItem : cartItems) {         
                    %>
                        <li>
                            <h4>
                            <b>Product:</b> <%=cartItem.getProduct().getProductName()%>,
                            <b>Unit Price:</b> <%=cartItem.getProduct().getUnitPrice()%>$,
                            <b>Qty:</b> <%=cartItem.getProductQty()%>, 
                            <b>Price:</b> <%=cartItem.getProduct().getUnitPrice() * cartItem.getProductQty()%>$
                            <% totalPrice += cartItem.getProduct().getUnitPrice() * cartItem.getProductQty(); %>  
                            <form action="CreateOrder" method="post"> <%-- CartUpdateDelete --%>
                                <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                                <input type="hidden" name="productid" value="<%=cartItem.getProduct().getProductId()%>"/>
                                <input type="number" name="quantity" value="<%=cartItem.getProductQty()%>"/>
                                <button type="submit" class="btn btn-primary" name="buttonaction" value="update">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"/>
                                </button>
                                <button type="submit" class="btn btn-primary" name="buttonaction" value="delete" >
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"/>
                                </button>
                            </form>
                            </h4>
                        </li>         
                    <%
                        }
                    %>
                    </ul>
                    <% if (totalPrice > 0) out.print("<h3><b>TOTAL PRICE:</b> " + totalPrice + "$</h3>"); %>
                    <li><a href="a-order-tests.jsp">Manual AddProductToCart</a></li>
                </div> 
                <% if (cartItems.isEmpty()) out.print("<h3 style='text-align: center;'>Shopping cart is empty.</h3>"); %>
                </c:when> <%-- /USER ONLY --%>    
                <c:otherwise><%-- ELSE --%>
                        <div class="inner-div" style="text-align: center; vertical-align: middle;">
                            <h1>Restricted page</h1>
                            <h3>Please <a href="login.jsp">log in</a>, in order to see items in your shopping cart!</h3> 
                        </div>
                </c:otherwise><%-- /ELSE --%>    
            </c:choose>      
                
                
                            
            <%--form action="CreateOrder" method="post">
                <!-- Hidden field product id -->
                Product id (1,2,3,4,5,6,7,8,9,11,13,14):<br>
                <input type="number" name="productid" value="13<%=product.getProductId()%>"/>
                <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                <br>Product qty:<br>
                <input type="number" name="quantity" value="1">
                <br>
                <input type="submit" value="Add to Cart">
            </form--%> 
             

                
            <div class="inner-div">

            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
