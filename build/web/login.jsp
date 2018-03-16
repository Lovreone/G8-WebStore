<%-- 
    Document   : login
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="head-meta.jsp"%>     
    </head>
    <body>
        <%@include file="main-nav.jsp"%>      
        <div class="container stylish-div-background">
            <h2>Login</h2>

            <div class="inner-div">

                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 pretty-form-bckg">
                        <c:if test="${sessionScope.userid != null}"><!-- LOGGED IN -->           
                            <div class="inner-div" style="text-align: center; vertical-align: middle;">
                                <h1>You are already logged in!</h1>
                                <h3>Go to your <a href="dashboard.jsp">Dashboard</a>.</h3>
                                <!-- Redirect to dashboard if LoggedIn is now automated (main-nav) -->
                            </div>
                        </c:if> <!-- /LOGGED IN -->
                        <c:if test="${sessionScope.userid == null}"> <!-- LOGGED OUT -->
                            <form class="form-horizontal pretty-form-padding" action="Login" method="post">

                                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-2 control-label">Email:</label>
                                    <div class="col-sm-9">
                                        <input type="email" maxlength="45" class="form-control" id="inputEmail" placeholder="Email" name="email" required autofocus/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-2 control-label">Password:</label>
                                    <div class="col-sm-9">
                                        <input type="password" maxlength="45" class="form-control" id="inputPassword" placeholder="Password" name="password" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Sign in</button>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <p class="validation-error">${errormessage}</p>
                                        <p>Don't have an account? Register <a href="register.jsp">here</a>!</p>
                                        <p>Trouble logging in? Contact <a href="mailto:support@webstore.com?subject=Login problem">administrator</a>.</p>  
                                    </div> 
                                </div>                    
                            </form>
                        </c:if> <!-- /LOGGED OUT -->   
                    </div>
                    <div class="col-md-3"></div>
                </div>  <!-- /.row -->

            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>

    </body>
</html>
