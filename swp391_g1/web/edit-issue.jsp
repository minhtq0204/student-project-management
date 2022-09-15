<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title> Edit Criteria</title>

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

                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="issueedit" name="issueedit" action="EditIssueController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Edit Issue</span></h5>
                                            </div>
                                            <input name="id" value="${issue.issueID}" hidden="">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Issue Title (*) : </label>
                                                    <input type="text" class="form-control" name="issuetitle" value="${issue.issueTitle}" required="">
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Function Name : </label>
                                                    <select name="functionname" class="form-control">
                                                        <c:forEach items="${listFunction}" var="function" >
                                                            <c:choose>
                                                                <c:when test="${function.functionID == issue.functionID}">
                                                                    <option selected value="${function.functionID}">${function.functionName}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option  value="${function.functionID}">${function.functionName}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Assign Name : </label>
                                                    <select name="assignname" class="form-control">
                                                        <c:forEach items="${listUser}" var="user">
                                                            <c:choose>
                                                                <c:when test="${user.userId == issue.assignID}">
                                                                    <option selected value="${user.userId}">${user.full_name}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${user.userId}">${user.full_name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Team Name : </label>
                                                    <select name="teamname" class="form-control" value="${requestScope.teamname}" >
                                                        <c:forEach items="${listTeam}" var="team">
                                                            <c:choose>
                                                                <c:when test="${team.teamID == issue.teamID}">
                                                                    <option selected value="${team.teamID}">${team.teamName}</option> 
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${team.teamID}">${team.teamName}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Gitlab URL : </label>
                                                    <input type="text" class="form-control" name="giturl" value="${issue.gitlabURL}">
                                                </div>
                                            </div>
                                           
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <label>Due Date : </label>
                                                        <input type="text" class="form-control" name="duedate" value="${issue.dua_date}" required="">
                                                    </div>
                                                </div>
                                            
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Description : </label>
                                                    <textarea class="form-control" name="description" >${issue.description}</textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <c:if test="${issue.status eq 1}">
                                                       <input type="radio"  name="status" value="0"> Closed
                                                        <input type="radio" checked="" name="status" value="1"> Open
                                                        <input type="radio" name="status" value="2"> Pending
                                                        
                                                    </c:if>
                                                    <c:if test="${issue.status eq 0}">
                                                        <input type="radio" checked=""  name="status" value="0"> Closed
                                                        <input type="radio"  name="status" value="1"> Open
                                                        <input type="radio" name="status" value="2"> Pending
                                                    </c:if>
                                                        <c:if test="${issue.status eq 2}">
                                                        <input type="radio"   name="status" value="0"> Closed
                                                        <input type="radio"  name="status" value="1"> Open
                                                        <input type="radio" checked="" name="status" value="2"> Pending
                                                    </c:if>
                                                        
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <button type="submit" onclick="valdate()" class="btn btn-primary">Submit</button>
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
        <script>
                                                            function valdate() {
                                                                var regdate = /^(19[0-9][0-9]|20[0-9][0-9])\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/;
                                                                if (issueedit.duedate.value.match(regdate)) {
                                                                    return true;
                                                                } else {
                                                                    alert("! please Enter the Date in this Format 'YYYY-MM-DD'");
                                                                    issueedit.duedate.value = "";
                                                                    issueedit.duedate.focus();
                                                                    return false;
                                                                }
                                                            }
        </script>

    </body>


    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>