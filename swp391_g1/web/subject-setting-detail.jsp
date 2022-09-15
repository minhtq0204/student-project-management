<%-- 
    Document   : add-user
    Created on : May 31, 2022, 7:03:50 PM
    Author     : mac
--%>

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

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="updatesubjectsetting" name="updatesubjectsetting" action="EditSubjectSettingController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Edit Subject Setting</span></h5>
                                            </div>
                                            <input name="id" value="${x.settingID}" hidden="">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Subject Name : </label>
                                                    <select name="subjectname" class="form-control" >
                                                        <c:forEach items="${listSubject}" var="subject" >
                                                            <c:choose>
                                                                <c:when test="${subject.subjectID == x.subjectID}">
                                                                    <option selected value="${subject.subjectID}">${subject.subjectName}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${subject.subjectID}">${subject.subjectName}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Type: </label>
                                                    <select name="typename" class="form-control" >
                                                        <c:forEach items="${listType}" var="type">
                                                            <c:choose >
                                                                <c:when test="${type.typeId == x.typeID}">
                                                                    <option selected value="${type.typeId}">${type.name}</option>
                                                                </c:when>   
                                                                <c:otherwise>
                                                                    <option  value="${type.typeId}">${type.name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Setting title : </label>
                                                    <input type="text" class="form-control" name="settingtitle" value="${x.settingTitle}">
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Setting value : </label>
                                                    <input type="text" class="form-control" name="settingvalue"  value="${x.settingValue}">
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description : </label>
                                                    <textarea class="form-control" name="description" >${x.description}</textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <c:if test="${x.status eq 1}">
                                                        <input type="radio"  name="status" value="0"> Inactive
                                                        <input type="radio" checked="" name="status" value="1"> Active
                                                    </c:if>
                                                    <c:if test="${x.status eq 0}">
                                                        <input type="radio" checked="" name="status" value="0"> Inactive
                                                        <input type="radio"  name="status" value="1"> Active
                                                    </c:if>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-6">
                                                <button type="submit" onclick="valdate()" class="btn btn-primary">Submit</button>
                                            </div>
                                        </div>

                                </div>
                                </form>
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
