<%-- 
    Document   : team-list
    Created on : Jun 17, 2022, 4:53:10 PM
    Author     : mac
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- Mirrored from preschool.dreamguystech.com/html-template/students.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:43 GMT -->

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Preskool - Students</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            .button-team-status {
                background: none!important;
                border: none;
                padding: 0!important;
                /*optional*/

                /*input has OS specific font-family*/
                color: white;
                text-decoration: none;
                cursor: pointer;
            }
            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

        </style>
    </head>

    <body>
        <c:choose>
            <c:when test="${stt.equals('1')}">
                <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                    <div class="toast" data-autohide="false">
                        <div class="toast-header bg-success">
                            <strong class="mr-auto text-white"><h4>Create Successfully</h4></strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            Create new team successfully.
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${stt.equals('2')}">
                <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                    <div class="toast" data-autohide="false">
                        <div class="toast-header bg-success">
                            <strong class="mr-auto text-white"><h4>Edit Successfully</h4></strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            Edit team successfully.
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

        <div class="main-wrapper">
            <div class="header">
                <%@include file="header.jsp" %>
            </div>
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <%@include file="sidebarleft_admin.jsp" %>
                    </div>
                </div>
            </div>
            <div class="page-wrapper">
                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Team List</h3>
                                <!--                     <ul class="breadcrumb">
                                                        <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                                        <li class="breadcrumb-item active">Users</li>
                                                     </ul>-->
                            </div>
                            <form action="searchTeam" method="get">
                                <div class="col-auto text-right float-right">
                                    <!--<form> -->
                                    Class &ensp; <select name="classID" style="background-color: #f5f4f1;
                                                         border-color: #FFBC53;">
                                        <option selected value="0">All class</option>
                                        <c:forEach items="${listClass}" var="class">
                                            <option ${classID == class.classId ?"selected":""} value="${class.classId}"> ${class.classCode} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;
                                    Status: &ensp; <select name="status" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="2">All status</option>
                                        <option ${status == 0 ?"selected":""} value="0">Inactive</option>
                                        <option ${status == 1 ?"selected":""} value="1">Active</option>                   
                                    </select>
                                    &emsp;
                                    Search: &ensp; <input type="text" name="txtSearch" value="${txtSearch}" style="background-color: #f5f4f1;
                                                          border-color: #FFBC53;">
                                    &ensp;
                                    
                                    <button type="submit" class="btn btn-outline-primary mr-2"><i class="fas fa-search"></i> Search</button>
                                    <a href="addTeam" class="btn btn-outline-primary mr-2"><i class="fas fa-plus"></i> Add
                                        team</a>

                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card card-table">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-center mb-0">
                                            <thead>
                                                <tr>
                                                    <th>Team name</th>
                                                    <th>Class</th>
                                                    <th>Topic code</th>
                                                    <th>Topic name</th>
                                                    <th>Status</th>
                                                    <th class="text-center">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listTeam}" var="x">
                                                    <tr>
                                                        <td>${x.teamName}</td>
                                                        <td>${x.classCode}</td>
                                                        <td>${x.topicCode}</td>
                                                        <td>${x.topicName}</td>
                                                        <td>
                                                            <c:if test="${x.status  == 1}">
                                                                Active
                                                            </c:if>
                                                            <c:if test="${x.status == 0}">
                                                                Inactive
                                                            </c:if>
                                                        </td>
                                                        <td class="text-center">
                                                            <div class="actions">
                                                                <a href="editTeam?id=${x.teamID}" class="btn btn-sm bg-success-light mr-2">
                                                                    <i class="fas fa-pen"></i>
                                                                </a>
                                                                <a href="detailsTeam?id=${x.teamID}" class="btn btn-sm bg-success-light mr-2">
                                                                    <i class="fas fa-eye"></i>
                                                                </a>    
                                                                <a class="btn btn-sm bg-success-light mr-2">
                                                                    <form method="post" action="changeTeamStatus">
                                                                        <input type="text" name="id" value="${x.teamID}" hidden="">
                                                                        <c:if test="${x.status == 1}">
                                                                            <button class="button-team-status" type="submit" onclick="myFunction()"> Deactivate </button>
                                                                        </c:if>
                                                                        <c:if test="${x.status == 0}">
                                                                            <button class="button-team-status" type="submit" onclick="myFunction()"> Activate </button>
                                                                        </c:if>


                                                                    </form>   
                                                                </a>    

                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="col-auto text-right float-right">
                                            <div class="pagination">
                                                <a href="#" >&laquo;</a>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <a href="team?index=${i}">${i}</a> 
                                                </c:forEach>
                                                <a href="#">&raquo;</a>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <footer>
                    <p>Copyright © 2022 Group 1 - SE1602_JS</p>
                </footer>
            </div>
        </div>



        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/datatables/datatables.min.js"></script>
        <script src="assets/js/script.js"></script>
        <script>
            $(document).ready(function () {
                $(".toast").toast({delay: 4000});
                $('.toast').toast('show');
            });
        </script>
        <script>
            function myFunction() {
                let text;
                if (confirm("Do you want change status ?") == true) {
                    text = "You pressed OK!";
                } else {

                }
                document.getElementById("demo").innerHTML = text;
            }
        </script>
    </body>
    <!-- Mirrored from preschool.dreamguystech.com/html-template/students.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:49 GMT -->

</html>
