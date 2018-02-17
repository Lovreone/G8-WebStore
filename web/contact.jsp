<%-- 
    Document   : contact
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Contact us</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%>
        <div class="container stylish-div-background">
            <h2>Contact us</h2>

            <div class="inner-div">
                <div class="row">
                    <div class="col-md-6">
                        <img src="images/contact-map.png" class="img-responsive" alt="Store location"/>
                    </div>
                    <div class="col-md-6">
                        <h4>Contact information</h4> 
                        <p><b>New York outlet:</b></p>
                        <ul class="contact-items">
                            <li><span class="glyphicon glyphicon-home" aria-hidden="true"></span>118 W 81st St, New York, NY 10024</li>
                            <li><span class="glyphicon glyphicon glyphicon-globe" aria-hidden="true"></span><a href="http://ny.webstore.com" target="blank">ny.webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:ny-office@webstore.com?subject=Website visitor">ny-office@webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>+1 443-994-1860</li> 
                        </ul>
                        <p><b>Los Angeles outlet:</b></p> 
                        <ul class="contact-items">
                            <li><span class="glyphicon glyphicon-home" aria-hidden="true"></span>513 W Compton Blvd, Los Angeles, CA 90220</li>
                            <li><span class="glyphicon glyphicon glyphicon-globe" aria-hidden="true"></span><a href="http://la.webstore.com" target="blank">la.webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:la-office@webstore.com?subject=Website visitor">la-office@webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>+1 213-974-3211</li>
                        </ul>
                        <p><b>Washington DC outlet:</b></p> 
                        <ul class="contact-items">
                            <li><span class="glyphicon glyphicon-home" aria-hidden="true"></span>2469 Pennsylvania Ave NW, Washington, DC 20037</li>
                            <li><span class="glyphicon glyphicon glyphicon-globe" aria-hidden="true"></span><a href="http://wa.webstore.com" target="blank">wa.webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:wa-office@webstore.com?subject=Website visitor">wa-office@webstore.com</a></li>
                            <li><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>+1 888-807-2111</li>
                        </ul>
                    </div>
                </div>
            </div>

        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
