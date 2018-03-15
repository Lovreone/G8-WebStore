<%-- 
    Document   : shop-checkout
    Created on : Mar 09, 2018, 16:50:10 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Checkout</h2>
            
            <div class="inner-div">
                
                <form class="form-horizontal" action="CompleteOrder" method="post">
                    <div class="row">
                        
                        <!-- LEFT COLUMN - SHIPPING INFORMATION-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-8">
                                    <h3>Shipping information</h3>
                                </div>
                            </div>
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
                            <div class="form-group"> <!-- 3-9 4-8(Responsive ok)-->
                                <label for="inputStreetAddress" class="col-sm-4 control-label">Street address</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" maxlength="95" id="inputStreetAddress" placeholder="Street address" name="street_address" value="" required/>
                                </div>
                            </div>
                        </div>
                        
                        <!-- RIGHT COLUMN - BILLING INFORMATION-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-8">
                                    <h3>Billing information</h3>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="selectCcType" class="col-sm-4 control-label">Card type</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="selectCcType" name="cc_type">
                                        <option value="Visa">Visa</option>
                                        <option value="MasterCard">MasterCard</option>
                                    </select>
                                </div>
                            </div>   
                            <div class="form-group">
                                <label for="inputCcNumber" class="col-sm-4 control-label">CC number</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="23" id="inputCcNumber" placeholder="XXXX XXXX XXXX XXXX" name="cc_number" value="" autocomplete="off" required/> 
                                    <!-- CHANGE CC NUMBER TO STRING IN DB!!! -->
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputExpMonth" class="col-sm-4 control-label">Expiration date</label>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" min="1" max="12" id="inputExpMonth" placeholder="MM" name="exp_month" value="" autocomplete="off" required/> 
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" min="2018" max="2030" id="inputExpYear" placeholder="YYYY" name="exp_year" value="" autocomplete="off" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCardHolderName" class="col-sm-4 control-label">Card Holder</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="45" id="inputCardHolderName" placeholder="Card Holder Name" name="card_holder_name" value="" autocomplete="off" required/> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCvvNumber" class="col-sm-4 control-label">CVV</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="number" min="1" max="9999" id="inputCvvNumber" placeholder="CVV Number" name="cvv_number" value="" autocomplete="off" required/>
                                </div>
                            </div>        
                        </div>
                        
                        <!-- SUBMIT BUTTON AND OTHER LINKS -->
                        <div>
                            <div class="panel-body one-third">  
                                <p><a href='shopping-cart.jsp'>Go back to cart?</a></p>
                                <p><a href='product-list.jsp'>Add more products to cart?</a></p>
                            </div>
                            <div class="panel-body one-third"></div>
                            <div class="panel-body last-third">
                                <table class="float-right">
                                    <tr>
                                        <td colspan="3" class="aligned-right">
                                        <button type="submit" class="btn btn-primary btn-lg" name="buttonaction" value="purchase">
                                            <span class="glyphicon glyphicon-ok-sign" aria-hidden="true" value="purchase"></span>Complete Purchase
                                        </button>
                                        </td>
                                    </tr>
                                </table>   
                            </div>
                            <div class="panel-body clear"></div>
                        </div>
                          
                    </div>      
                </form>
                
                  
            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
