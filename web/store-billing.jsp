<%-- 
    Document   : store-billing
    Created on : Mar 09, 2018, 16:50:10 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Billing information</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>Checkout: Step 2</h2>

            <div class="inner-div">

                <form class="form-horizontal" action="CompleteOrder" method="post">
                    <div class="row"> <!-- BILLING INFORMATION FORM (Columns 2-8-2) -->
                        
                        <div class="col-md-2"></div>
                        <div class="col-md-8 pretty-form-bckg">
                            <div class="form-group">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-8">
                                    <h3>Billing information</h3>
                                </div>
                            </div>
                            <input type="hidden" name="userid" value="${sessionScope.userid}"/>
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
                            <div class="form-group">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-8">
                                    <a class="btn btn-default" href='store-cart.jsp'>Back to Cart</a>
                                    <a class="btn btn-default" href='store-shipping.jsp'>< Temp </a>
                                    <button type="submit" class="btn btn-primary btn-lg" name="buttonaction" value="completepurchase">
                                        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>Confirm &AMP; Pay
                                    </button> 
                                    <a class="btn btn-default" href='store-success.jsp'>Temp ></a>
                                </div>
                            </div>     
                        </div>
                        <div class="col-md-2"></div>   
                        
                    </div> <!-- /.row -->      
                </form>
                

            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
