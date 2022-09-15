<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
    .error-code{
        color: red;
    }
</style>
<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title> Iteration Details</title>
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
                                <h3 class="page-title">Add Iteration</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="iteration-details.jsp">Iteration</a></li>
                                    <li class="breadcrumb-item active">Add Iteration</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="iterationdetails" name="iterationdetails" action="IterationDetailsController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Iteration Details</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Subject Code : </label>
                                                    <select name="subjectID" value="${requestScope.subjectID}" >
                                                        <c:forEach items="${listSubject}" var="subject">
                                                            <option value="${subject.subjectID}">${subject.subjectCode}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Iteration Name (*) : </label>
                                                    <input type="text" class="form-control" name="itername" value="${requestScope.subjectname}" required="">
                                                    <div class="error-name" id="error-name">${requestScope.errorname}
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Duration(days) (*): </label>
                                                    <input type="text" class="form-control" name="iterdate" value="${requestScope.iterdate}"  required="">
                                                    <div class="error-date" id="error-date">${requestScope.errordate}
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" name="status" value="1">
                                                    &nbsp;&nbsp;Active
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" name="status" value="0">
                                                    &nbsp;&nbsp;Inactive
                                                </div>
                                            </div> 

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description: </label>
                                                    <textarea class="form-control" name="description" value="${requestScope.description}"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
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
    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>