<%-- 
    Document   : cms-product-edit
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page import="com.app.domains.*"%>
<%@page import="com.app.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CMS: Edit Product</title>
        <%@include file="head-meta.jsp"%>
    </head>
    <body>
        <%@include file="main-nav.jsp"%> 
        <div class="container stylish-div-background">
            <h2>CMS: Edit Product</h2>
            <div class="inner-div">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10 pretty-form-bckg"> 

                        <c:choose>
                            <c:when test="${sessionScope.userid != null && sessionScope.isadmin == true}"> <%-- ADMIN ONLY --%>

                                <%
                                    String productId = request.getParameter("productid");
                                    ProductDao pd = new ProductDao();
                                    Product p = pd.getSingleProduct(productId);
                                %>

                                <ul class="nav nav-tabs padding-top">
                                    <li role="presentation"><a href="cms-product-manage.jsp">Manage all products</a></li>
                                    <li role="presentation"><a href="cms-product-create.jsp">Add a new product</a></li>
                                    <li role="presentation" class="active"><a href="#">Edit existing product</a></li> 
                                </ul>

                                <h3>Editing product #<%=productId%></h3>

                                <form class="form-horizontal" method="post" action="ImageUpload" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="imageUpload" class="col-sm-3 control-label">Product Image (*):</label>
                                        <div class="col-sm-5">
                                            <input type="hidden" name="productid" value="<%=p.getProductId()%>"/>
                                            <input type="file" class="form-control" id="imageUpload" name="picture" required/>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="submit" class="btn btn-default" name="buttonaction" value="edit">Upload picture</button>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <p class="success-message">${uploadsuccess}</p>
                                            <p class="validation-error">${uploaderror}</p>
                                        </div>
                                    </div>  
                                </form> 

                                <% // Automatic population of imagePath on image upload, user has to save
                                    String oldPath = p.getProductDetails().getImagePath();
                                    String newPath = (String) request.getAttribute("imgpath");
                                    if (newPath != null && !newPath.isEmpty()) {
                                        oldPath = newPath;
                                    }
                                %>

                                <form class="form-horizontal" action="ManageProducts" method="post">
                                    <input type="hidden" name="productid" value="<%=p.getProductId()%>"/>
                                    <div class="form-group">
                                        <label for="inputImagePath" class="col-sm-3 control-label">ImagePath (*):</label>
                                        <div class="col-sm-7">
                                            <input type="text" maxlength="200" class="form-control" id="inputImagePath" placeholder="Image location" name="imagepath" value="<%= oldPath%>" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputProductName" class="col-sm-3 control-label">Model name (*):</label>
                                        <div class="col-sm-7">
                                            <input type="text" maxlength="45" class="form-control" id="inputProductName" placeholder="Product name" name="productname" value="<%= p.getProductName()%>" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputUnitPrice" class="col-sm-3 control-label">Price (*):</label>
                                        <div class="col-sm-7">
                                            <input type="number" min="1" max="99999" class="form-control" id="inputUnitPrice" placeholder="Unit price" name="unitprice" value="<%= p.getUnitPrice()%>" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputQtyInStock" class="col-sm-3 control-label">Stock (*):</label>
                                        <div class="col-sm-7">
                                            <input type="number" min="1" max="99999" class="form-control" id="inputQtyInStock" placeholder="Quantity in stock" name="stockqty" value="<%= p.getStockQuantity()%>" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDescription" class="col-sm-3 control-label">Description (*):</label>
                                        <div class="col-sm-7">
                                            <textarea rows="5" maxlength="550" class="form-control" id="inputDescription" placeholder="Description" name="description" required><%= p.getProductDetails().getDescription()%></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button type="submit" class="btn btn-primary" name="buttonaction" value="modify" onClick="return areYouSure()">Save changes</button>
                                            <a href="cms-product-manage.jsp" class="btn btn-default" role="button">Cancel</a>
                                        </div>
                                    </div> 

                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <p class="validation-error">${errormessage}</p>
                                            <p class="success-message">${successmessage}</p><br/>
                                        </div> 
                                    </div> 

                                </form> 

                                <script>
                                    function areYouSure() {
                                        if (!confirm('You are about to permanently change this product!'))
                                            return false;
                                    }
                                </script>

                                <!-- Requires additional testing
                                    Add in DataFormatValidation (Servlet) -->   

                            </c:when><%-- /ADMIN ONLY --%>
                            <c:when test="${sessionScope.userid != null && sessionScope.isadmin == false}"><%-- USER ONLY --%>
                                <div class="inner-div centered-content">
                                    <h1>Restricted page</h1>
                                    <h3>You do not have permissions to access this page!</h3> 
                                    <h3>Go to your <a href="dashboard.jsp">Dashboard</a>.</h3>
                                </div>
                            </c:when> <%-- /USER ONLY --%>       
                            <c:when test="${sessionScope.userid == null}"> <%-- LOGGED OUT --%>
                                <div class="inner-div centered-content">
                                    <h1>Restricted page</h1>
                                    <h3>You must be logged in in order to access this page!</h3> 
                                    <h3>Click here to <a href="login.jsp">login</a>.</h3>
                                </div>
                            </c:when> <%-- /LOGGED OUT --%>
                            <c:otherwise><%-- ELSE --%>
                                <div class="inner-div centered-content">
                                    <h1>Restricted page</h1>
                                    <h3>Oops... Something went wrong!</h3> 
                                    <h3>Go back to <a href="index.jsp">Home page</a>.</h3>
                                </div>
                            </c:otherwise><%-- /ELSE --%>    
                        </c:choose>   
                    </div>
                    <div class="col-md-1"></div> 
                </div>


            </div> <!-- /.inner-div -->
        </div> <!-- /.container -->
        <%@include file="footer.jsp"%>
    </body>
</html>
