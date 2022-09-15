<%-- 
    Document   : classlist
    Created on : Jun 19, 2022, 9:33:34 PM
    Author     : Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Class List</title>

        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

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
                                Create new class successfully.
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
                                Edit class successfully.
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
                                <h3 class="page-title">Class List</h3>
                            </div>
                            <form action="SearchClassController" method="get">
                                <div class="col-auto text-right float-right">
                                    Status: &ensp; <select name="status" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="2">All status</option>
                                        <option ${status == 0 ?"selected":""} value="0">Inactive</option>
                                        <option ${status == 1 ?"selected":""} value="1">Active</option>                   
                                    </select>
                                    &emsp;
                                    Subject &ensp; <select name="subjectID" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="0">All subject</option>
                                        <c:forEach items="${listSubject}" var="subject">
                                            <option ${subjectID == subject.subjectID ?"selected":""} value="${subject.subjectID}"> ${subject.subjectCode} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;
                                    Trainer &ensp; <select name="trainerID" style="background-color: #f5f4f1;
                                                           border-color: #FFBC53;">
                                        <option selected value="0">All trainer</option>
                                        <c:forEach items="${listTrainer}" var="user">
                                            <option ${trainerID == user.userId ?"selected":""} value="${user.userId}"> ${user.full_name} </option>
                                        </c:forEach>
                                    </select>
                                    &emsp;
                                    <button type="submit" class="btn btn-outline-primary mr-2"><i class="fas fa-search"></i> Search</button>
                                    <a href="AddClassController" class="btn btn-outline-primary mr-2"><i class="fas fa-plus"></i> Add Class</a>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class=" table table-stripped">
                                            <thead>
                                                <tr>
                                                    <th>Class code</th>
                                                    <th>Subject code</th>
                                                    <th>Trainer name</th>
                                                    <th>Block 5 status</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${lstClass}" var="data">
                                                    <tr>
                                                        <td>
                                                            ${data.classCode}
                                                        </td>
                                                        <td>
                                                            ${data.subjectCode}
                                                        </td>
                                                        <td>
                                                            ${data.trainerName}
                                                        </td>
                                                        <td>
                                                            <c:if test="${data.block5Class==1}">
                                                                True
                                                            </c:if>
                                                            <c:if test="${data.block5Class==0}">
                                                                False
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${data.status==1}">
                                                                Active
                                                            </c:if>
                                                            <c:if test="${data.status==0}">
                                                                Inactive
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <a href="ClassDetailController?id=${data.classId}" class="btn btn-sm bg-success-light mr-2">
                                                                <span><i class="fas fa-pen"></i> Edit</span>
                                                            </a>
                                                            <c:if test="${data.status==1}">
                                                                <a href="ChangeStatusClassController?id=${data.classId}"
                                                                   class="btn btn-sm bg-success-light mr-2" onclick="myFunction()"> 
                                                                    <span>Deactivate</span>
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${data.status==0}">
                                                                <a href="ChangeStatusClassController?id=${data.classId}" 
                                                                   class="btn btn-sm bg-success-light mr-2" onclick="myFunction()"> 
                                                                    <span>Activate</span>
                                                                </a> 
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-auto text-right float-right">
                                        <div class="pagination">
                                            <a href="#" >&laquo;</a>
                                            <c:forEach begin="1" end="${endPage}" var="i">
                                                <a href="ClassListController?index=${i}">${i}</a> 
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



</body>
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="assets/plugins/datatables/datatables.min.js"></script>
<script src="assets/js/script.js"></script>
<script>
                                                               $(document).ready(function () {
                                                                   $(".toast").toast({delay: 2000});
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
</html>
