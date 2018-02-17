<%-- 
    Document   : test1
    Created on : Sep 13, 2017, 10:25:34 PM
    Author     : Lovreone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>T1: JSTL</title>
    </head>
    <body>

        <div class="container stylish-div-background">
            <h2>Test1: JSTL</h2>

            <div class="inner-div">

                <!-- Stampanje vrednosti -->
                <c:out value="Hello World!"/><br/>

                <!-- Deklaracija i inicijalizacija promelnjive -->
                <c:set var="userStatus" value="1"/>
                ${userStatus} <br/>
                <c:out value="${userStatus}"/><br/>
                <%= pageContext.getAttribute("userStatus")%> <br/>

                <!-- Banalizovana verzija try-catch bloka -->
                <c:catch var="myException">
                    <% int x = 10 / 0;%>
                </c:catch>
                ${myException}<br/>

                <!-- Pandan switch strukturi-test vise slucajeva -->
                <c:set var="randomNum"><%= (int) (Math.random() * 100)%></c:set>
                <c:choose>
                    <c:when test="${randomNum%2==0}" >
                        <c:out value="Broj ${randomNum} deljiv sa 2" />
                    </c:when>
                    <c:when test="${randomNum%2==0 && randumNum1%3==0}" >
                        <c:out value="Broj ${randomNum} deljiv sa 2 i sa 3" />
                    </c:when>
                    <c:otherwise>
                        <c:out value="Broj ${randomNum} nije deljiv ni sa 2 ni sa 3" />
                        <div>
                            <a href="http://www.google.com">google.com</a>
                        </div>
                    </c:otherwise>
                </c:choose><br/>

                <!-- Pandan if strukturi-jedna pitalica -->
                <c:if test="${sessionScope.userId != null}"> <!-- Zatrebace kasnije, sada ne radi -->
                    <c:out value="You are logged in" />
                </c:if><br/>

                <!-- Pandan for petlji-->   
                <c:forEach begin="1" end="100" var="broj"> 
                    <c:if test="${broj%3==0 && broj%2==0}">
                        <c:out value="${broj}" />
                    </c:if>
                </c:forEach> <br/>

                <!-- Pandan forEach petlji-->
                <%
                    String[] stringovi = {"Pera", "Mika", "Zika", "Laza"};
                    request.setAttribute("stringovi", stringovi);
                %>
                <ul>
                    <c:forEach var="val" items="${stringovi}">
                        <li> <c:out value="${val}" /> </li>
                        </c:forEach>
                </ul> 
                <br/>
                
                <!-- Koriscenje Delimitera za podelu stringa na tokene -->
                <ul>
                    <c:forTokens delims="." var="val" items="Pera.Mika.Zika.Laza">
                        <li> <c:out value="${val}" /> </li>
                    </c:forTokens>
                </ul>
                <br/>

        </div> <!-- /.inner-div -->

    </div> <!-- /.container -->
</body>
</html>
