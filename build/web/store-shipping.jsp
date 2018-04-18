<%-- 
    Document   : store-shipping
    Created on : Mar 09, 2018, 16:50:10 PM
    Author     : Lovreone
--%>

<%@page import="com.app.domains.OrderDetails"%>
<%@page import="com.app.domains.Order"%>
<%@page import="com.app.dao.OrderDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shipping information</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Checkout: Step 1</h2>

            <div class="inner-div">

                <c:choose> 
                    <c:when test="${sessionScope.userid != null && sessionScope.isadmin == false}"><%-- USER ONLY --%>
                        <%
                            String userId = request.getSession().getAttribute("userid").toString();
                            OrderDao oDao = new OrderDao();
                            Order o = oDao.getActiveOrder(userId);
                            int orderId = o.getOrderId();
                            OrderDetails od;

                            String fName = request.getSession().getAttribute("fname").toString();
                            String lName = request.getSession().getAttribute("lname").toString();
                            String city = ""; 
                            String country = "";
                            String streetAddress = "";

                            if(oDao.orderDetailsExist(orderId)) {
                                od = o.getOrderDetails();
                                fName = od.getFirstName();
                                lName = od.getLastName();
                                city = od.getCity();
                                country = od.getCountry();
                                streetAddress = od.getStreetAddress();
                            }
                        %>
   
                    <form class="form-horizontal" action="CompleteOrder" method="post">
                        <div class="row"> <!-- SHIPPING INFORMATION FORM (Columns 2-8-2) -->

                            <div class="col-md-2"></div>
                            <div class="col-md-8 pretty-form-bckg">
                                <div class="form-group">
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-8">
                                        <h3>Shipping information</h3>
                                    </div>
                                </div>
                                <input type="hidden" name="userid" value="${sessionScope.userid}"/>
                                <div class="form-group"> 
                                    <label for="inputFirstName" class="col-sm-4 control-label">First name</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" maxlength="45" id="inputFirstName" placeholder="First name" name="first_name" value="<%=fName%>" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputLastName" class="col-sm-4 control-label">Last name</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" maxlength="45" id="inputLastName" placeholder="Last name" name="last_name" value="<%=lName%>" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCity" class="col-sm-4 control-label">City</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" maxlength="45" id="inputCity" placeholder="City" name="city" value="<%=city%>" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCountry" class="col-sm-4 control-label">Country</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" maxlength="45" id="inputCountry" placeholder="Country" name="country" value="<%=country%>" required/>
                                    </div>
                                </div>
                                <div class="form-group"> 
                                    <label for="inputStreetAddress" class="col-sm-4 control-label">Street address</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" maxlength="95" id="inputStreetAddress" placeholder="Street address" name="street_address" value="<%=streetAddress%>" required/>
                                    </div>
                                </div>     
                                <div class="form-group"> 
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-8">
                                        <a class="btn btn-default" href='store-cart.jsp'>Back to Cart</a>
                                        <button type="submit" class="btn btn-primary btn-lg" name="buttonaction" value="shippinginfo">
                                            <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>Proceed to Payment
                                        </button>
                                        <a class="btn btn-default" href='store-billing.jsp'>Temp ></a>
                                    </div>   
                                </div>
                            </div>        
                            <div class="col-md-2"></div>   

                        </div>  <!-- /.row --> 
                    </form>
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
