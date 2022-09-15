<%-- 
    Document   : edit-user
    Created on : May 31, 2022, 7:12:15 PM
    Author     : mac
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div>
            <%@ include file = "header.jsp" %>
        </div>
        <div>
            <%@ include file = "slidebarleft.jsp" %>
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
                                Create New User Successfully.
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
                                Edit User Successfully.
                            </div>
                        </div>
                    </div>
                </c:when>
               
                <c:otherwise>
                    
                </c:otherwise>
            </c:choose>
        <div class="main-wrapper">
            <div class="page-wrapper">
                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Edit Users</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="user-list.jsp">Users</a></li>
                                    <li class="breadcrumb-item active">Edit User</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <form method="post" action="editUser">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form>
                                            <div class="row">
                                                <div class="col-12">
                                                    <h5 class="form-title"><span>User Information</span></h5>
                                                </div>
                                                <input name="id" value="${user.userId}" hidden="">
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Roll Number</label>
                                                        <input type="text" class="form-control" name="roll_num" value="${user.roll_number}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Full Name</label>
                                                        <input type="text" class="form-control" name="full_name" value="${user.full_name}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Gender</label>
                                                        <select class="form-control" name="gender" >
                                                            <option value="1" <c:if test="${user.gender eq 1}">selected</c:if>>Female</option>
                                                            <option value="0" <c:if test="${user.gender eq 0}">selected</c:if>>Male</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Date of Birth</label>
                                                            <div>
                                                                <input type="text" class="form-control" name="dob" value="${user.dateOfBirth}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Email</label>
                                                        <input type="text" class="form-control" name="email" value="${user.email}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Mobile Number</label>
                                                        <input type="text" class="form-control" name="mobile" value="${user.mobile}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Avatar Link</label>
                                                        <input type="text" class="form-control" name="avatar_link" value="${user.avatar_link}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Facebook Link</label>
                                                        <input type="text" class="form-control" name="face_link" value="${user.face_link}">
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Role</label>
                                                        <select class="form-control" name="role_id">
                                                            <option value="0" <c:if test="${user.role_id eq 0}">selected</c:if>>Admin</option>
                                                            <option value="1" <c:if test="${user.role_id eq 1}">selected</c:if>>Student</option>
                                                            <option value="2" <c:if test="${user.role_id eq 2}">selected</c:if>>Trainer</option>
                                                            <option value="3" <c:if test="${user.role_id eq 3}">selected</c:if>>Author</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" name="status" value="1"<c:if test="${user.status eq 1}">checked=""</c:if>>
                                                        &nbsp;&nbsp;Active
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="radio" name="status" value="0"<c:if test="${user.status eq 0}">checked=""</c:if>>
                                                    &nbsp;&nbsp;Inactive
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
