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
                            Create new function successfully.
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
                            Edit function successfully.
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${stt.equals('3')}">
                <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                    <div class="toast" data-autohide="false">
                        <div class="toast-header bg-success">
                            <strong class="mr-auto text-white"><h4>Export Successfully</h4></strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            Export function list successfully.
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${stt.equals('4')}">
                <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                    <div class="toast" data-autohide="false">
                        <div class="toast-header bg-success">
                            <strong class="mr-auto text-white"><h4>Edit Successfully</h4></strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            Import function list successfully.
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
                                <h3 class="page-title">Function List</h3>
                                <!--                     <ul class="breadcrumb">
                                                        <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                                        <li class="breadcrumb-item active">Users</li>
                                                     </ul>-->
                            </div>
                            <form action="searchFunction" method="get">
                                <div class="col-auto text-right ">
                                    <!--<form> -->
                                    Team &ensp; <select name="teamID" style="background-color: #f5f4f1;
                                                        border-color: #FFBC53;">
                                        <option selected value="0">All team</option>
                                        <c:forEach items="${listTeam}" var="team">
                                            <option ${teamID == team.teamID ?"selected":""} value="${team.teamID}"> ${team.teamName} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;
                                    Feature &ensp; <select name="featureID" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="0">All feature</option>
                                        <c:forEach items="${listFeature}" var="feature">
                                            <option ${featureID == feature.featureID ?"selected":""} value="${feature.featureID}"> ${feature.featureName} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;
                                    Status: &ensp; <select name="status" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="4">All status</option>
                                        <option ${status == 0 ?"selected":""} value="0">Pending</option>
                                        <option ${status == 1 ?"selected":""} value="1">Planned</option>
                                        <option ${status == 2 ?"selected":""} value="2">Evaluated</option> 
                                        <option ${status == 3 ?"selected":""} value="3">Rejected</option> 
                                    </select>
                                    &emsp;
                                    Search: &ensp; <input type="text" name="txtSearch" value="${txtSearch}" style="background-color: #f5f4f1;
                                                          border-color: #FFBC53;">
                                    &ensp;
                                    <button type="submit" class="btn btn-outline-primary mr-2"><i class="fas fa-search"></i> Search</button>


                                </div>
                            </form>
                        </div>
                        <br>
                        <div class="text-right">
                            <a href="addFunction" class="btn btn-outline-primary mr-2"><i class="fas fa-plus"></i> Add function</a>
                            <a href="exportExcelFunction" class="btn btn-outline-primary mr-2"><i class="fas fa-file-export"></i>  Export</a>
<!--                            <a href="importExcelFunction" class="btn btn-outline-primary mr-2"><i class="fas fa-file-import"></i>  Import</a>-->
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
                                                    <th>Function name</th>
                                                    <th>Team name</th>
                                                    <th>Feature name</th>
                                                    <th>Owner name</th>
                                                    <th>Complexity</th>
                                                    <th>Status</th>
                                                    <th class="text-center">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listFunction}" var="x">
                                                    <tr>
                                                        <td>${x.functionName}</td>
                                                        <td>${x.teamName}</td>
                                                        <td>${x.featureName}</td>
                                                        <td>${x.ownerName}</td>
                                                        <td>
                                                            <c:if test="${x.complexityID  == 0}">
                                                                Simple
                                                            </c:if>
                                                            <c:if test="${x.complexityID == 1}">
                                                                Medium
                                                            </c:if>
                                                            <c:if test="${x.complexityID == 2}">
                                                                Complex
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${x.status  == 0}">
                                                                Pending
                                                            </c:if>
                                                            <c:if test="${x.status == 1}">
                                                                Planned
                                                            </c:if>
                                                            <c:if test="${x.status == 2}">
                                                                Evaluated
                                                            </c:if>
                                                            <c:if test="${x.status == 3}">
                                                                Rejected
                                                            </c:if>
                                                        </td>
                                                        <td class="text-center">
                                                            <div class="actions">
                                                                <a href="editFunction?functionID=${x.functionID}" class="btn btn-sm bg-success-light mr-2">
                                                                    <i class="fas fa-pen"></i>
                                                                </a>
                                                                <a href="detailsFunction?id=${x.functionID}" class="btn btn-sm bg-success-light mr-2">
                                                                    <i class="fas fa-eye"></i>
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
                                                    <a href="functionList?index=${i}">${i}</a> 
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

        <!--
        
        -->

        <script src="assets/js/jquery-3.6.0.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/datatables/datatables.min.js"></script>
        <script src="assets/js/script.js"></script>
         <script>
                $(document).ready(function () {
                    $(".toast").toast({delay: 4000});
                    $(".toast").toast("show");

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
