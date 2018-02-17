<%-- 
    Document   : bane-test
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="com.app.domains.User"%>
<%@page import="java.util.List"%>
<%@page import="com.app.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TEST page</title> <!-- CHANGE!!!!-->
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>TEST page</h2> <!-- CHANGE!!!!-->

            <div class="inner-div">

                
            <%-- // TESTING LOGIN METHOD
                UserDao ud = new UserDao();

                List<User> lista1 = ud.loginCheck("user@user.com", "dadas");

                User u1 = !lista1.isEmpty() ? lista1.get(0) : null;
                out.print("NOVO FALSE: " + u1);
                for (User elem : lista1) {
                    out.print(elem);   
                }

                List<User> lista2 = ud.loginCheck("bane@whitecitysoft.com", "1234");
                
                User u2 = lista2.get(0);
                out.print("NOVO TRUE: " + u2);
                for (User elem : lista2) {
                    out.print(elem);
                }
                 out.print(u2.getEmail());
                 out.print(u2.getPassword());
                 out.print(u2.isIsAdmin());
                 out.print(u2.getUserDetails().getFirstName());
                 out.print(u2.getUserDetails().getLastName());
            --%> 
                
                
            </div> <!-- /.inner-div -->

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
