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
                                <h3 class="page-title">Edit Subject</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="ListSubjectController">Subject</a></li>
                                    <li class="breadcrumb-item active">Edit Subject</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="editsubject" name="editsubject" action="EditSubjectController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Edit Subject</span></h5>
                                            </div>
                                            <input name="id" value="${subject.subjectID}" hidden="">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Subject Code (*) : </label>
                                                    <input type="text" class="form-control" name="subjectcode" value="${subject.subjectCode}" required="">
                                                    <div class="error-code" id="error-code">${requestScope.errorcode}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Subject Name (*) : </label>
                                                    <input type="text" class="form-control" name="subjectname" value="${subject.subjectName}"  required="">
                                                    <div class="error-name" id="error-name">${requestScope.errorname}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Author Name : </label>
                                                    <select name="authorID">
                                                        <c:forEach items="${listAuthor}" var="user">
                                                            <c:choose>
                                                                <c:when  test="${user.userId == subject.authorID}">
                                                                    <option selected value="${user.userId}" >${user.full_name}</option>
                                                                </c:when>         
                                                                <c:otherwise>
                                                                    <option value="${user.userId}" >${user.full_name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" name="status" value="1"<c:if test="${subject.status eq 1}">checked=""</c:if>>
                                                        &nbsp;&nbsp;Active
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="radio" name="status" value="0"<c:if test="${subject.status eq 0}">checked=""</c:if>>
                                                    &nbsp;&nbsp;Inactive
                                                </div>
                                            </div> 
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Description: </label>
                                                        <textarea class="form-control" name="description" value="${subject.description}"></textarea>
                                                </div>
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


    <script src="css/jquery-3.6.0.min.js"></script>

    <script src="css/popper.min.js"></script>
    <script src="css/bootstrap.min.js"></script>

    <script src="css/jquery.slimscroll.min.js"></script>

    <script src="css/script.js"></script>
</body>

<!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>