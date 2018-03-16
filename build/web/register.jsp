<%-- 
    Document   : register
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="com.app.dao.*"%>
<%@page import="com.app.domains.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%>
        <div class="container stylish-div-background">
            <h2>Register</h2>

            <div class="inner-div">

                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8 pretty-form-bckg">
                        <c:if test="${sessionScope.userid != null}"><!-- LOGGED IN -->           
                            <div class="inner-div" style="text-align: center; vertical-align: middle;">
                                <h1>You are already logged in!</h1>
                                <h3>Go to your <a href="dashboard.jsp">Dashboard</a>.</h3>
                            </div>
                        </c:if> <!-- /LOGGED IN -->
                        <c:if test="${sessionScope.userid == null}"> <!-- LOGGED OUT -->
                            <form class="form-horizontal pretty-form-padding" action="UserRegistration" method="post">
                                <div class="form-group">
                                    <label for="inputFirstName" class="col-sm-4 control-label">First name (*):</label>
                                    <div class="col-sm-7">
                                        <input type="text" maxlength="45" class="form-control" id="inputFirstName" placeholder="First name" name="firstname" value="${param.firstname}" required autofocus/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputLastName" class="col-sm-4 control-label">Last name (*):</label>
                                    <div class="col-sm-7">
                                        <input type="text" maxlength="45" class="form-control" id="inputLastName" placeholder="Last name" name="lastname" value="${param.lastname}" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-4 control-label">Email (*):</label>
                                    <div class="col-sm-7">
                                        <input type="email" maxlength="45" class="form-control" id="inputEmail" placeholder="Email" name="email" value="${param.email}" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-4 control-label">Password (*):</label>
                                    <div class="col-sm-7">
                                        <input type="password" maxlength="45" class="form-control" id="inputPassword" placeholder="Enter Password" name="password1" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="repeatPassword" class="col-sm-4 control-label">Repeat Password (*):</label>
                                    <div class="col-sm-7">
                                        <input type="password" maxlength="45" class="form-control" id="repeatPassword" placeholder="Repeat Password" name="password2" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-sm-8">
                                        <button type="submit" class="btn btn-primary">Sign up</button>
                                    </div>
                                </div>      
                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-sm-8">
                                        <p>Already have an account? <a href="login.jsp">Login here!</a></p>
                                        <p class="validation-error">${passmismatch}${statusmessage}</p>
                                        <p class="success-message">${registersuccess}</p><br/>
                                    </div>
                                </div>        
                            </form>
                        </c:if> <!-- /LOGGED OUT -->
                    </div>
                    <div class="col-md-2"></div>
                </div> <!-- /.row -->

            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>

    </body>
</html>
