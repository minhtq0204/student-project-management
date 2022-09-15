<%-- 
    Document   : home
    Created on : 16 May, 2022, 11:58:44 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <!--        <div class="home-page">
                    <div class="home__header">
                        <div class="left__content">
                            <div class="left__content--logo">STM</div>
                            <div class="left__content--nav">
                                <div class="nav-item">
                                    <a href="home.jsp">Home</a>
                                </div>
                                <div class="nav-item">
                                    <a href="#">Product</a>
                                </div>
                                <div class="nav-item">
                                    <a href="#">Share</a>
                                </div>
                            </div>
                        </div>
    </div>-->
        <div class="home-page">
                <div class="home__header">
                    <%@ include file = "header.jsp" %>
                </div>
                <div class="home__content"></div>
<!--                <div class="home__footer">
                    <%@ include file = "footer.jsp" %>
                </div>-->
            
        </div>
        <script>
        </script>
    </body>
</html>
