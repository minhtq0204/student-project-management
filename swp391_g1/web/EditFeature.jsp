<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title> Subject Details</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">


        <link rel="stylesheet" href="css/bootstrap.min.css">

        <link rel="stylesheet" href="css/fontawesome.min.css">
        <link rel="stylesheet" href="css/all.min.css">

        <link rel="stylesheet" href="css/style.css">
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
                                <h3 class="page-title">Edit Feature</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="FeatureList">Feature</a></li>
                                    <li class="breadcrumb-item active">Edit Feature</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="FeatureController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Edit Feature</span></h5>
                                            </div>
                                            <input name="id" value="${f.featureID}" hidden="">
                                            
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Feature Name : </label>
                                                    <input type="text" class="form-control" name="FeatureName" value="${f.featureName}"  required="">
                                                    <div class="error-name" id="error-name">${requestScope.errorname}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Topic Name : </label>
                                                    <select name="teamId">
                                                        <c:forEach items="${listTeam}" var="t">
                                                            <c:choose>
                                                                <c:when  test="${f.teamID == t.teamID}">
                                                                    <option selected value="${t.teamID}" >${t.topicName}</option>
                                                                </c:when>         
                                                                <c:otherwise>
                                                                    <option value="${t.teamID}" >${t.topicName}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                                    <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Class : </label>
                                                    <select name="classCode">
                                                        <c:forEach items="${listClass}" var="t">
                                                            <c:choose>
                                                                <c:when  test="${f.classID == t.classId}">
                                                                    <option selected value="${t.classId}" >${t.classCode}</option>
                                                                </c:when>         
                                                                <c:otherwise>
                                                                    <option value="${t.classId}" >${t.classCode}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                                     <div class="col-12 col-sm-6">
                                                <div class="form-group" >
                                                    <label>Status : </label>
                                                    <br>
                                                    <c:if test="${f.status eq 1}">
                                                        <input type="radio" checked="" name="status" value="1">Active
                                                        <input type="radio"  name="status" value="0">Inactive
                                                    </c:if>
                                                    <c:if test="${f.status eq 0}">
                                                        <input type="radio"  name="status" value="1">Active
                                                        <input type="radio" checked=""  name="status" value="0">Inactive
                                                    </c:if>
                                                </div>
                                            </div>
                                                    
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary">EDIT Feature</button>
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


        <script src="css/jquery-3.6.0.min.js"></script>

        <script src="css/popper.min.js"></script>
        <script src="css/bootstrap.min.js"></script>

        <script src="css/jquery.slimscroll.min.js"></script>

        <script src="css/script.js"></script>
    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT 
</html>