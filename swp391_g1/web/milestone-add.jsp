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
                  <%@include file="sidebarleft_admin.jsp" %>
               </div>
            </div>
         </div>
         <div class="page-wrapper">
             
            <div class="content container-fluid">
               <div class="page-header">
                  <div class="row align-items-center">
                     <div class="col">
                        <h3 class="page-title">Add Milestone</h3>
                        <ul class="breadcrumb">
                           <li class="breadcrumb-item"><a href="team">Milestones</a></li>
                           <li class="breadcrumb-item active">Milestone</li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-12">
                     <div class="card">
                        <div class="card-body">
                             <form   action="addMilestone" method="post">
                              <div class="row">
                                  
                                 <div class="col-12">
                                    <h5 class="form-title"><span>Milestone Details</span></h5>
                                 </div>
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>Class Code</label>
                                       <select name="classID" class="form-control">
                                           <c:forEach items="${listClass}" var="class">
                                               <option value="${class.classId}">${class.classCode}</option>
                                            </c:forEach>
                                       </select>
                                    </div>
                                 </div>
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>Milestone name</label>
                                       <input type="text" class="form-control" name="MileStoneName" required="">
                                    </div>
                                 </div>
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>Iteration name</label>
                                       <div>
                                          <select name="iterationID" class="form-control">
                                           <c:forEach items="${iterations}" var="c">
                                               <option value="${c.iterationID}">${c.iterationName}</option>
                                            </c:forEach>
                                       </select>
                                       </div>
                                    </div>
                                 </div>
                                           
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>From date</label>
                                       <div>
                                       <input type="text" class="form-control" name="fromDate">
                                       <div class="error-code" id="error-code" style="color: red">${requestScope.errorname}
                                       </div>
                                       </div>
                                    </div>
                                 </div>
                                       
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>To date</label>
                                       <input type="text" class="form-control" name="toDate">
                                    </div>
                                 </div>
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                       <label>Description</label>
                                       <textarea class="form-control" name="description"></textarea>
                                    </div>
                                 </div>      
                                 <div class="col-12 col-sm-6">
                                    <div class="form-group">
                                        <label>Status : </label>
                                        <br>
                                        <input type="radio" name="status" value="0"> Closed
                                        &nbsp;&nbsp;
                                        <input type="radio" checked="" name="status" value="1"> Open
                                        &nbsp;&nbsp;
                                        <input type="radio" name="status" value="2"> Canceled
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
      <script src="assets/js/jquery-3.6.0.min.js"></script>
      <script src="assets/js/popper.min.js"></script>
      <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
      <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
      <script src="assets/js/script.js"></script>
   </body>
   <!-- Mirrored from preschool.dreamguystech.com/html-template/add-student.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT -->
</html>
