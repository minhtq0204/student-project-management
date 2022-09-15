<%-- 
    Document   : add-setting
    Created on : Jun 8, 2022, 3:03:43 PM
    Author     : mac
--%>

<style>
    .error-code{
        color: red;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-student.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Preskool - Students</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
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
                        <%@include file="sidebarleft_admin.jsp" %>
                    </div>
                </div>
            </div>
            <div class="page-wrapper">

                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Add Setting</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="SettingController">Setting</a></li>
                                    <li class="breadcrumb-item active">Add Setting</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form method="post" action="addSetting">
                                        <div class="row">

                                            <div class="col-12">
                                                <h5 class="form-title"><span>Setting Details</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Groups*</label>
                                                    <select name="typeId" class="form-control">
                                                        <c:forEach items="${listType}" var="type">
                                                            <option value="${type.typeId}">${type.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Values</label>
                                                    <input type="text" class="form-control" name="settingValue" 
                                                           value="${requestScope.settingValue}" required="">
                                                    <div class="error-code" id="error-code" style="color:red">${requestScope.errorcode}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                    <textarea class="form-control" name="settingNote" value="${requestScope.settingNote}"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" name="status" value="1"<c:if test="${team.status eq 1}">checked=""</c:if>>
                                                        &nbsp;&nbsp;Active
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="radio" name="status" value="0"<c:if test="${team.status eq 0}">checked=""</c:if>>
                                                    &nbsp;&nbsp;Inactive
                                                </div>
                                            </div> 
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </div>

                                        </div>
                                    </form>

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
        <script src="assets/js/script.js"></script>
    </body>
    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-student.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>
