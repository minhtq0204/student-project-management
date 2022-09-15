<%-- 
    Document   : team-add
    Created on : Jun 17, 2022, 4:53:21 PM
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
        <div class="main-wrapper">
            <div class="header">
                <%@include file="header.jsp" %>
            </div>
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <%@include file="sidebarleft_trainer.jsp" %>
                    </div>
                </div>
            </div>
            <div class="page-wrapper">

                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Details Function</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="functionList">Functions</a></li>
                                    <li class="breadcrumb-item active">Function</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="teamadd" name="myForm"  action="addFunction" method="post">
                                        <div class="row">

                                            <div class="col-12">
                                                <h5 class="form-title"><span>Function Information</span></h5>
                                            </div>
                                            <input name="id" value="${function.functionID}" hidden="">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Team Name</label>
                                                    <select name="teamID" class="form-control" disabled="true">
                                                        <c:forEach items="${listTeam}" var="team">
                                                            <option value="${team.teamID}">${team.teamName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Function name</label>
                                                    <input type="text" class="form-control" name="functionName" value="${function.functionName}" readonly="">
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Feature name</label>
                                                    <select name="featureID" class="form-control" disabled="true">
                                                        <c:forEach items="${listFeature}" var="feature">
                                                            <option value="${feature.featureID}">${feature.featureName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Access roles</label>
                                                    <input type="text" class="form-control" name="accessRoles"
                                                           value="${function.accessRoles}" readonly="">
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                    <textarea class="form-control" name="description" readonly="">${function.description}</textarea>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Complexitiy</label>
                                                    <select class="form-control" name="complexityID" disabled="true">
                                                        <option>Select complexitiy</option>
                                                        <option value="0" <c:if test="${function.complexityID eq 0}">selected</c:if>>Simple</option>
                                                        <option value="1" <c:if test="${function.complexityID eq 1}">selected</c:if>>Medium</option>
                                                        <option value="2" <c:if test="${function.complexityID eq 2}">selected</c:if>>Complex</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Owner Name</label>
                                                    <select name="ownerID" class="form-control" disabled="true">
                                                        <c:forEach items="${listOwner}" var="owner">
                                                            <option value="${owner.userId}">${owner.full_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Priority</label>
                                                    <select class="form-control" name="priority" disabled="true">
                                                        <option>Select priority</option>
                                                        <option value="0" <c:if test="${function.priority eq 0}">selected</c:if>>1</option>
                                                        <option value="1" <c:if test="${function.priority eq 1}">selected</c:if>>2</option>
                                                        <option value="2" <c:if test="${function.priority eq 2}">selected</c:if>>3</option>
                                                        <option value="3" <c:if test="${function.priority eq 3}">selected</c:if>>4</option>
                                                        <option value="4" <c:if test="${function.priority eq 4}">selected</c:if>>5</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status</label>
                                                    <select class="form-control" name="status" disabled="true">
                                                        <option>Select status</option>
                                                        <option value="0" <c:if test="${function.status eq 0}">selected</c:if>>Pending</option>
                                                        <option value="1" <c:if test="${function.status eq 1}">selected</c:if>>Planned</option>
                                                        <option value="2" <c:if test="${function.status eq 2}">selected</c:if>>Evaluated</option>
                                                        <option value="3" <c:if test="${function.status eq 3}">selected</c:if>>Rejected</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </div>
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
</div>
<script src="assets/js/jquery-3.6.0.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/js/script.js"></script>
</body>
<!-- Mirrored from preschool.dreamguystech.com/html-template/add-student.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>