<%-- 
    Document   : sidebarleft_trainer
    Created on : Jun 17, 2022, 4:59:53 PM
    Author     : mac
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Dashboard</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="header">
            <%@include file="header.jsp" %>
        </div>
        <div class="sidebar" id="sidebar">
            <div class="sidebar-inner slimscroll">
                <div id="sidebar-menu" class="sidebar-menu">
                    <ul>
                        <li class="menu-title">
                            <span>Main Menu</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserController"><i class="fas fa-user-graduate"></i> <span>  User</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="SettingController"><i class="fas fa-star"></i> <span> Setting</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ListSubjectController"><i class="fas fa-plus"></i> <span> Subject</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ListSubjectSettingController"><i class="fas fa-book"></i> <span> Subject Setting</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ListIterationController"><i class="fas fa-chalkboard-teacher"></i> <span> Iteration</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ListCriteriaController"><i class="fas fa-user-graduate"></i> <span> Criteria</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ClassListController"><i class="fas fa-book"></i> <span> Class</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MilestoneListController"><i class="fas fa-baseball-ball"></i> <span> Milestone</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="team"><i class="fas fa-basketball-ball"></i> <span> Team</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="FeatureList"><i class="fas fa-football-ball"></i> <span> Feature</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="functionList"><i class="fas fa-hotel"></i> <span> Function</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ListIssueController"><i class="fas fa-book"></i> <span> Issue</span></a>
                        </li>

                        <li class="menu-title">
                            <span>Management</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>