<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title> Criteria Details</title>

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
                                <h3 class="page-title">Add Criteria</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="">Criteria</a></li>
                                    <li class="breadcrumb-item active">Add Criteria</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="criteriadetails" name="criteriadetails" action="CriteriaDetailsController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Criteria Details</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Subject Name : </label>
                                                    <select name="subjectname" class="form-control" value="${requestScope.subjectname}" >
                                                        <c:forEach items="${listSubject}" var="subject" >
                                                            <option value="${subject.subjectID}">${subject.subjectCode}</option>
                                                        </c:forEach>
                                                        <div class="error-code" id="error-code">${requestScope.errorcode}
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Iteration Name : </label>
                                                    <select name="itername" class="form-control" value="${requestScope.itername}" >
                                                        <c:forEach items="${listIteration}" var="iteration">
                                                            <option value="${iteration.iterationID}">${iteration.iterationName}</option>
                                                        </c:forEach>
                                                        <div class="error-code" id="error-code">${requestScope.errorcode}
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Criteria Name (*) : </label>
                                                    <input type="text" class="form-control" name="criterianame" value="${requestScope.criterianame}" required="">
                                                    <div class="error-name" id="error-name">${requestScope.errorname}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Evaluation weight (%) (*) : </label>
                                                    <input type="text" class="form-control" name="weight" value="${requestScope.weight}" pattern="[0-9]+" required="">
                                                    <div class="error-name" id="error-name">${requestScope.errorcode}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Criteria order : </label>
                                                    <input type="text" class="form-control" name="criorder" pattern="[0-9]+" value="${requestScope.criorder}">
                                                    <div class="error-date" id="error-date">${requestScope.errordate}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label> Team Evaluation : </label>
                                                    <br>
                                                    <input type="radio" name="team" value="True">True
                                                    <input type="radio" name="team" value="False">False
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Max loc : </label>
                                                    <input type="text" class="form-control" name="loc" pattern="[0-9]+" value="${requestScope.loc}"  required="">
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" name="status" value="1">Active
                                                    <input type="radio" name="status" value="0">Inactive
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description : </label>

                                                    <textarea class="form-control" name="description" value="${requestScope.description}"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-6">
                                                <button type="submit" class="btn btn-primary" >Submit</button>
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
        <script src="css/jquery-3.6.0.min.js"></script>
        <script src="css/popper.min.js"></script>
        <script src="css/bootstrap.min.js"></script>
        <script src="css/jquery.slimscroll.min.js"></script>
        <script src="css/script.js"></script>

    </body>


    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>