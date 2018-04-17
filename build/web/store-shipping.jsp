<%-- 
    Document   : store-shipping
    Created on : Mar 09, 2018, 16:50:10 PM
    Author     : Lovreone
--%>

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
                                    <input type="text" class="form-control" maxlength="45" id="inputFirstName" placeholder="First name" name="first_name" value="${sessionScope.fname}" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputLastName" class="col-sm-4 control-label">Last name</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="45" id="inputLastName" placeholder="Last name" name="last_name" value="${sessionScope.lname}" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCity" class="col-sm-4 control-label">City</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="45" id="inputCity" placeholder="City" name="city" value="" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCountry" class="col-sm-4 control-label">Country</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="45" id="inputCountry" placeholder="Country" name="country" value="" required/>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <label for="inputStreetAddress" class="col-sm-4 control-label">Street address</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="95" id="inputStreetAddress" placeholder="Street address" name="street_address" value="" required/>
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


            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
