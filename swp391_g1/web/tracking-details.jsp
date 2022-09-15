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

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form id="trackingdetails" name="trackingdetails" action="TrackingDetailsController" method="post" >
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Add Tracking</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Assigner Name : </label>
                                                    <select name="assignername" class="form-control" value="${requestScope.assignername}" >
                                                        <c:forEach items="${listAssigner}" var="user">
                                                            <option value="${user.userId}">${user.full_name}</option>
                                                        </c:forEach>

                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Assignee Name : </label>
                                                    <select name="assigneename" class="form-control" value="${requestScope.assigneename}" >
                                                        <c:forEach items="${listAssignee}" var="user">
                                                            <option value="${user.userId}">${user.full_name}</option>
                                                        </c:forEach>

                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Team Name : </label>
                                                    <select name="teamname" class="form-control" value="${requestScope.teamname}" >
                                                        <c:forEach items="${listTeam}" var="team">
                                                            <option value="${team.teamID}">${team.teamName}</option>
                                                        </c:forEach>

                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Function Name : </label>
                                                    <select name="functionname" class="form-control" value="${requestScope.functionname}" >
                                                        <c:forEach items="${listFunction}" var="function" >
                                                            <option value="${function.functionID}">${function.functionName}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Milestone : </label>
                                                    <select name="milestonename" class="form-control" value="${requestScope.teamname}" >
                                                        <c:forEach items="${listMilestone}" var="milestone">
                                                            <option value="${milestone.milestoneID}">${milestone.milestoneName}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>


                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Tracking Note : </label>
                                                    <input type="text" class="form-control" name="trackingnote" value="${requestScope.giturl}" required="">
                                                </div>
                                            </div>

                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Updates : </label>
                                                    <input type="text" class="form-control" name="updates"  value="${requestScope.duedate}" >
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Status : </label>
                                                    <br>
                                                    <input type="radio" checked="" name="status" value="1">Active
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
                                        <div class="col-12 col-sm-6">
                                            <button type="submit" onclick="valdate()" class="btn btn-primary">ADD TRACKING</button>
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
                                                    if (issuedetails.duedate.value.match(regdate)) {
                                                        return true;
                                                    } else {
                                                        alert("! please Enter the Date in this Format 'YYYY-MM-DD'");
                                                        issuedetails.duedate.value = "";
                                                        issuedetails.duedate.focus();
                                                        return false;
                                                    }
                                                }
        </script>

    </body>


    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>