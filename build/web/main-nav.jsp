<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String uri = request.getRequestURI();
    String pageName = uri.substring(uri.lastIndexOf("/") + 1);
    String navTheme = "navbar-default"; 
    
    /* Desc: Additional layer of security that disables the browser back button problem: 
    "Once user logges out, he is redirected to login page. By using browser's back button he 
    was able to go back to the secure page he was previously on. Refreshing the page would 
    hide the contents from the user". Also added redirects from secure pages to login page 
    Part of solution found here https://www.youtube.com/watch?v=gQLQ0t9B5yk */
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
    if ((pageName.equals("dashboard.jsp") || pageName.equals("cms-product-create.jsp") || 
                pageName.equals("cms-product-edit.jsp") || pageName.equals("cms-product-manage.jsp") || 
                pageName.equals("shopping-cart.jsp") || pageName.equals("shop-checkout.jsp")) 
                && session.getAttribute("userid") == null) {
        response.sendRedirect("login.jsp");
        
    } else if (pageName.equals("login.jsp") && session.getAttribute("userid") != null) {
        response.sendRedirect("dashboard.jsp");
    }
%>

<c:if test="${sessionScope.userid != null && sessionScope.isadmin == true}">
    <% navTheme = "navbar-inverse"; %>
</c:if>

<!-- Documentation http://getbootstrap.com/docs/3.3/components/#navbar -->
<!-- working example https://getbootstrap.com/docs/3.3/examples/navbar-fixed-top/-->
<!-- navbar-static-top OR navbar-fixed-top-->
<nav class="navbar <%=navTheme%> navbar-fixed-top" style="box-shadow: 0px 5px 20px #888888"> <!-- navbar-inverse -->
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">G8-WebStore</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="<%= pageName.equals("index.jsp") || pageName.equals("") ? "active" : ""%>"><a href="index.jsp">Home<span class="sr-only">(current)</span></a></li>
                <li class="<%= pageName.equals("about.jsp") ? "active" : ""%>"><a href="about.jsp">About</a></li>
                <li class="<%= pageName.equals("contact.jsp") ? "active" : ""%>"><a href="contact.jsp">Contact</a></li>
                <li class="<%= pageName.equals("product-list.jsp") ? "active" : ""%>"><a href="product-list.jsp">Products</a></li>
                
                <!-- Only Logged out -->
                <c:if test="${sessionScope.userid == null}"> 
                    <li class="<%= pageName.equals("login.jsp") ? "active" : ""%>"><a href="login.jsp">Login</a></li>
                </c:if>
                    
                <!-- Only Logged in -->    
                <c:if test="${sessionScope.userid != null}"> 
                    <li><a href="dashboard.jsp">Dashboard</a></li>
                </c:if>
                
                <!-- Only Admin -->     
                <c:if test="${sessionScope.userid != null && sessionScope.isadmin == true}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">CMS <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="cms-product-manage.jsp">CMS: Manage Products</a></li>
                            <li><a href="cms-product-create.jsp">CMS: Create a Product</a></li>
                            <!--li role="separator" class="divider"></li-->
                            <!--li><a href="tests/test1.jsp" target="blank">T1:JSTL Tests page</a></li-->   
                            <!--li><a href="http://localhost:8080/G8-JWP-c7v1/index.jsp" target="blank">Help project</a></li-->
                        </ul>
                    </li>
                </c:if>
                    
                <!-- Only Logged in -->   
                <c:if test="${sessionScope.userid != null}"> 
                    <p class="navbar-text">Hi, <b>${sessionScope.fname}</b></p>
                    <li>
                        <a href="Logout">
                         <span class="glyphicon glyphicon-off" aria-hidden="true"/>   
                        </a>
                    </li>
                </c:if>
                    
            </ul>
        </div>    

    </div><!-- /.container -->
</nav>