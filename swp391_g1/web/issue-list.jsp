x<%-- 
    Document   : user-list
    Created on : May 30, 2022, 6:55:44 PM
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
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>FPTU-Issue</title>
        <style>
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
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>
        <div class="main-wrapper">
            <div class="header">

                <%@include file="header.jsp" %>

            </div>
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <%@include file="slidebarleft.jsp" %>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${stt.equals('1')}">
                    <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                        <div class="toast" data-autohide="true">
                            <div class="toast-header bg-success">
                                <strong class="mr-auto text-white"><h4>Create Successfully</h4></strong>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                            </div>
                            <div class="toast-body">
                                Create New Issue Successfully.
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${stt.equals('2')}">
                    <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                        <div class="toast" data-autohide="true">
                            <div class="toast-header bg-success">
                                <strong class="mr-auto text-white"><h4>Edit Successfully</h4></strong>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                            </div>
                            <div class="toast-body">
                                Edit Issue Successfully.
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${stt.equals('3')}">
                    <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                        <div class="toast" data-autohide="true">
                            <div class="toast-header bg-success">
                                <strong class="mr-auto text-white"><h4>Import Successfully</h4></strong>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                            </div>
                            <div class="toast-body">
                                Import Issue File Successfully.
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>

            <div class="page-wrapper">
                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Issue List</h3>
                            </div>
                            <form action="SearchIssueController" method="get">

                                <div class="col-auto text-right float-right">
                                    <!--<form> -->
                                    Issue Title &ensp; <select name="issueTitle" style="background-color: #f5f4f1;
                                                               border-color: #FFBC53;">
                                        <option selected value="0">Issue Title</option>
                                        <c:forEach items="${listIssueTitle}" var="issue">
                                            <option ${issueTitle == issue.issueTitle ?"selected":""} value="${issue.issueTitle}"> ${issue.issueTitle} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;

                                    <button type="submit" class="btn btn-outline-primary mr-2"><i class="fas fa-search"></i> Search</button>
                                    <a href="IssueDetailsController" class="btn btn-outline-primary mr-2"><i class="fas fa-plus"></i> Add Issue</a>
                                    <a href="ExportExcelController" class="btn btn-outline-primary mr-2"><i class="fas fa-file-export"></i>Export</a>

                                </div>

                            </form>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card card-table">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-center mb-0 ">
                                                <thead>
                                                    <tr>      
                                                        <th class="text-left">Issue Tile</th>
                                                        <th class="text-left">Function Name</th>
                                                        <th class="text-left">Assign Name</th>
                                                        <th class="text-left">Team Name</th>
                                                        <th class="text-center">Status</th>
                                                        <th class="text-center">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listIssue}" var="issue">
                                                        <tr>

                                                            <td class="text-left">
                                                                ${issue.issueTitle}
                                                            </td>
                                                            <td class="text-left">
                                                                ${issue.functionName}
                                                            </td>
                                                            <td class="text-left">
                                                                ${issue.assignName}
                                                            </td>
                                                            <td class="text-left"> 
                                                                ${issue.teamName}
                                                            </td >
                                                            <td class="text-center">
                                                                <c:if test="${issue.status==1}">
                                                                    <span
                                                                        class="inline-flex px-5 py-2 font-semibold leading-5 text-green-800 bg-green-100 rounded-lg text-md "
                                                                        ><a> Open</a></span>
                                                                </c:if>
                                                                <c:if test="${issue.status==0}">
                                                                    <span
                                                                        class="inline-flex px-5 py-2 font-semibold leading-5 text-green-800 bg-green-100 rounded-lg text-md "
                                                                        ><a> Closed</a></span> 
                                                                </c:if>
                                                                <c:if test="${issue.status==2}">
                                                                    <span
                                                                        class="inline-flex px-5 py-2 font-semibold leading-5 text-green-800 bg-green-100 rounded-lg text-md "
                                                                        ><a> Pending</a></span> 
                                                                </c:if>
                                                            </td>
                                                            <td class="text-center">
                                                                <a href="EditIssueController?id=${issue.issueID}" class="btn btn-sm bg-success-light mr-2">
                                                                    <span>><i class="fas fa-pen"></i> Edit</span>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="col-auto text-right float-right">
                                                <div class="pagination">
                                                    <a href="#" >&laquo;</a>
                                                    <c:forEach begin="1" end="${endPage}" var="i">
                                                        <a href="ListIssueController?index=${i}">${i}</a> 
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


</html>
