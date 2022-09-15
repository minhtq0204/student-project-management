<%-- 
    Document   : add-user
    Created on : May 31, 2022, 7:03:50 PM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
    .error-email{
        color: red;
    }
</style>
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
                        <%@include file="slidebarleft.jsp" %>
                    </div>
                </div>
            </div>
            <div class="page-wrapper">

                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Add Users</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="UserController">Users</a></li>
                                    <li class="breadcrumb-item active">Add User</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <form name="myForm" method="post" action="UserController">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">

                                            <div class="col-12">
                                                <h5 class="form-title"><span>User Information</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Roll Number</label>
                                                    <input type="text" class="form-control" name="roll_num" value="${requestScope.roll_num}">
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Full Name</label>
                                                    <input type="text" class="form-control" name="full_name" required="" value="${requestScope.full_name}" id="name">

                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Gender</label>
                                                    <select class="form-control" name="gender">
                                                        <option>Select Gender</option>
                                                        <option value="1">Female</option>
                                                        <option value="0">Male</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Date of Birth</label>
                                                    <div>
                                                        <input type="text" class="form-control" value="${requestScope.dob}" name="dob">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Email</label>
                                                    <input type="text" class="form-control" name="email" value="${requestScope.email}" required="">
                                                    <div class="error-email" id="error-email">${requestScope.erroremail}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Mobile Number</label>
                                                    <input type="text" class="form-control" name="mobile" value="${requestScope.mobile}" required="">
                                                    <div class="error-mobile" id="error-mobile">${requestScope.errormobile}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Avatar Link</label>
                                                    <input type="text" class="form-control" value="${requestScope.avatar_link}" name="avatar_link" >
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Facebook Link</label>
                                                    <input type="text" class="form-control" value="${requestScope.face_link}" name="face_link" >
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Role</label>
                                                    <select class="form-control" name="role_id">
                                                        <option>Select Roles</option>
                                                        <option value="0">Admin</option>
                                                        <option value="1">Student</option>
                                                        <option value="2">Trainer</option>
                                                        <option value="3">Author</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Password</label>
                                                    <input type="text" class="form-control" value="${requestScope.password}" name="password">
                                                </div>
                                            </div> 
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
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
