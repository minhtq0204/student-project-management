<%-- 
    Document   : header
    Created on : May 26, 2022, 10:28:41 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Preskool - Dashboard</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="header">
            <div class="header-left">
                <a href="home.jsp" class="logo">
                    <img src="assets/img/logo.png" alt="Logo">
                </a>
                <!--                <a href="home.jsp" class="logo logo-small">
                                    <img src="assets/img/logo-small.png" alt="Logo" width="30" height="30">
                                </a>-->
            </div>
            <!--            <a href="javascript:void(0);" id="toggle_btn">
                            <i class="fas fa-align-left"></i>
                        </a>-->
            <!--            <div class="top-nav-search">
                            <form>
                                <input type="text" class="form-control" placeholder="Search here">
                                <button class="btn" type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>-->
            <a class="mobile_btn" id="mobile_btn">
                <i class="fas fa-bars"></i>
            </a>
            <ul class="nav user-menu">

                <c:if test="${sessionScope.account == null}">
                    <div style="display: flex;padding-top: 10px;" class="right__content">
                        <div class="login">
                            <div class="login-btn">
                                <a style="font-size: larger;font-weight: 400;text-decoration: none; padding-right: 10px; " href="login.jsp">Login</a>
                            </div>
                        </div>
                        <div class="register">
                            <div class="register-btn">
                                <a style="font-size: larger;font-weight: 400;text-decoration: none;padding-left: 10px; " href="register.jsp">Register</a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${sessionScope.account != null}">
                    <div  class="nav user-menu">
                        <div class="nav-item dropdown has-arrow">
                            <a href="" class="dropdown-toggle nav-link" data-toggle="dropdown">
                                <c:if test="${sessionScope.account.avatar_link == null}">
                                <span class="user-img"><img class="rounded-circle" src="http://cdn.onlinewebfonts.com/svg/img_264570.png" class="avatar-img rounded-circle"  width="31"></span>
                                </c:if>
                                <c:if test="${sessionScope.account.avatar_link != null}">
                                <span class="user-img"><img class="rounded-circle" src="${sessionScope.account.avatar_link}" class="avatar-img rounded-circle" width="31"></span>
                                </c:if>
                            </a>
                            <div class="dropdown-menu">
                                <div class="user-header">
                                    <div class="avatar avatar-sm">
                                        <c:if test="${sessionScope.account.avatar_link != null}">
                                            <img src="${sessionScope.account.avatar_link}" width="31" class="avatar-img rounded-circle" >
                                        </c:if>
                                        <c:if test="${sessionScope.account.avatar_link == null}">
                                        <img src="http://cdn.onlinewebfonts.com/svg/img_264570.png" width="31" class="avatar-img rounded-circle">
                                        </c:if>
                                    </div>
                                    <div class="user-text">
                                        <h6>${sessionScope.account.full_name}</h6>
                                        <p class="text-muted mb-0">Role</p>
                                    </div>
                                </div>
                                <a class="dropdown-item" href="dashboard.jsp">Dash board</a>
                                <a class="dropdown-item" href="userProfile.jsp">My Profile</a>
                                <a class="dropdown-item" href="changePassword.jsp">Change Password</a>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
                        </div>
                    </div>
                </c:if>

            </ul>
        </div>
    </body>
    <script src="assets/js/jquery-3.6.0.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/plugins/apexchart/apexcharts.min.js"></script>
    <script src="assets/plugins/apexchart/chart-data.js"></script>
    <script src="assets/js/script.js"></script>
</html>