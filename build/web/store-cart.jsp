<%-- 
    Document   : store-cart
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
            
            <div class="inner-div">
                
            <c:choose> 
                <c:when test="${sessionScope.userid != null && sessionScope.isadmin == false}"><%-- USER ONLY --%>
               
                <%
                    OrderDao od = new OrderDao();
                    Order order = od.getActiveOrder(String.valueOf(session.getAttribute("userid")));
                    Set<OrderProduct> cartItems = order.getOrderProducts();
                    int totalPrice = 0;
                    for (OrderProduct cartItem : cartItems) {
                %>       
                    
                    <!-- SINGLE CART ITEM -->
                    <div class="panel panel-default s-bottom-spacing">
                        <div class="panel-heading">
                            <h4 class="cart"><b><%=cartItem.getProduct().getProductName()%></b></h4>
                        </div>
                        <div class="panel-body custom-spacing-cart">
                            <table class="table table-responsive no-bottom-spacing">
                                <tr> 
                                    <th>Image:</th>
                                    <th>Quantity:</th>
                                    <th>Unit price:</th>
                                    <th></th>
                                </tr>
                                <tr>
                                    <td>
                                        <img src="<%=cartItem.getProduct().getProductDetails().getImagePath()%>" height="35 px" width="auto" alt=""/>
                                    </td>
                                    <td>
                                        <form action="ManageCart" method="post">
                                            <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                                            <input type="hidden" name="productid" value="<%=cartItem.getProduct().getProductId()%>"/>
                                            <input class="margin-offset" type="number" min="1" max="5" name="quantity" value="<%=cartItem.getProductQty()%>"/>
                                            <button type="submit" class="btn btn-primary btn-sm" name="buttonaction" value="update">
                                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>Update
                                            </button>
                                        </form>
                                    </td>
                                    <td class="vertical-center">
                                        <h4 class="cart">
                                            <%=cartItem.getProduct().getUnitPrice()%> $
                                        </h4>
                                    </td>
                                    <td class="aligned-right">
                                        <form action="ManageCart" method="post">
                                            <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                                            <input type="hidden" name="productid" value="<%=cartItem.getProduct().getProductId()%>"/> 
                                            <input type="hidden" name="quantity" value="<%=cartItem.getProductQty()%>"/>
                                            <button type="submit" class="btn btn-primary btn-sm" name="buttonaction" value="delete" >
                                                <span class="glyphicon glyphicon-trash no-padding" aria-hidden="true"></span>
                                            </button>
                                        </form>
                                    </td> 
                                </tr>
                            </table>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item aligned-right">
                                <h4 class="cart">Subtotal: 
                                    <%=cartItem.getProduct().getUnitPrice() * cartItem.getProductQty()%> $
                                </h4>
                            </li>
                            <% totalPrice += cartItem.getProduct().getUnitPrice() * cartItem.getProductQty();%>
                        </ul>   
                    </div>      
                        
                <%
                    }
                    if (totalPrice > 0) { 
                %>
                    
                    <!-- TOTALS DIV -->                       
                    <div>
                        <div class="panel-body one-third">  
                            <p><a href="product-list.jsp">< Continue Shopping</a></p>
                        </div>
                        <div class="panel-body one-third"></div>
                        <div class="panel-body last-third">
                            <table class="float-right">
                                <tr>
                                    <td><h4 class="cart">Shipping:</h4></td>
                                    <td>&nbsp;</td>
                                    <td>FREE</td>
                                </tr>
                                <tr>
                                    <td><h4>Total:</h4></td>
                                    <td>&nbsp;</td>
                                    <td><h4><%=totalPrice%> $</h4></td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="aligned-right">
                                        <form action="store-shipping.jsp">
                                            <input type="hidden" name="userid" value="1"/>
                                            <input type="hidden" name="productid" value="1"/>
                                            <button type="submit" class="btn btn-primary btn-lg" name="buttonaction" value="checkout">
                                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Checkout
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="panel-body clear"></div>
                    </div>
             
                <%  
                    } 
                    if (cartItems.isEmpty()) {
                %>
                    
                    <!-- EMPTY CART MESSAGE -->    
                    <div class="inner-div centered-content">
                        <h3>Shopping cart is empty</h3>
                        <p><a href="product-list.jsp">Add some products to cart?</a></p>
                    </div>
                    
                <%
                    }
                %>
                
                </c:when> <%-- /USER ONLY --%>    
                <c:otherwise><%-- ELSE --%>
                    
                    <!-- PLEASE LOGIN MESSAGE -->
                    <div class="inner-div centered-content">
                        <h3>Restricted content</h3>
                        <h4>Please <a href="login.jsp">log in</a>, in order to see contents of your shopping cart!</h4> 
                    </div>
                    
                </c:otherwise><%-- /ELSE --%>    
            </c:choose>
                                         
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
