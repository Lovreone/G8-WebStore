<%-- 
    Document   : index
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container" style="vertical-align: auto;">
            
            <div style="text-align: center;">
                <h2>Welcome to our super awesome cell phone store!!!</h2>
            </div>
            
            <!-- Documentation: https://getbootstrap.com/docs/3.3/javascript/#carousel-usage-->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">

                    <div class="item active">
                        <img src="images/slider/slide1.jpg" alt="Slide1 image">
                        <!--div class="carousel-caption">
                            <h3>Slide 1 title</h3>
                            <p>Slide 1 description</p>
                        </div-->
                    </div>

                    <div class="item">
                        <img src="images/slider/slide2.jpg" alt="Slide 2 image">
                        <!--div class="carousel-caption">
                            <h3>Slide 2 title</h3>
                            <p>Slide 2 description</p>
                        </div-->
                    </div>
                    
                    <div class="item">
                        <img src="images/slider/slide3.jpg" alt="Slide 3 image">
                        <!--div class="carousel-caption">
                            <h3>Slide 3 title</h3>
                            <p>Slide 3 description</p>
                        </div-->
                    </div>
                    
                    <div class="item">
                        <img src="images/slider/slide4.jpg" alt="Slide 4 image">
                        <!--div class="carousel-caption">
                            <h3>Slide 4 title</h3>
                            <p>Slide 4 description</p>
                        </div-->
                    </div>


                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div style="text-align: center;">
                <h2>Check out our list of <a href="product-list.jsp">currently avilable products</a>!</h2>
            </div>
            
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
