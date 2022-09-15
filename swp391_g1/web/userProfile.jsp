<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <!-- Mirrored from preschool.dreamguystech.com/html-template/profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:38 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Preskool - Profile</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="home__header">
            <%@ include file = "header.jsp" %>
        </div>
        <div class="main-wrapper">
            
            <div class="page-wrapper">
                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row">
                            <div class="col">
                                <h3 class="page-title">Profile</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Profile</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="profile-header">
                                <div class="row align-items-center">
                                    <div class="col-auto profile-image">
                                        <a href="#">
                                            <c:if test="${sessionScope.account.avatar_link != null}">
                                                <img src="${sessionScope.account.avatar_link}" >
                                            </c:if>
                                            <c:if test="${sessionScope.account.avatar_link == null}">
                                                <img src="http://cdn.onlinewebfonts.com/svg/img_264570.png">
                                            </c:if>
                                        </a>
                                    </div>
                                    <div class="col ml-md-n2 profile-user-info">
                                        <h4 class="user-name mb-0">${sessionScope.account.full_name}</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="profile-menu">
                                <a class="nav-link" data-toggle="tab" href="#per_details_tab">About</a>
                            </div>
                            <div class="tab-content profile-tab-cont">
                                <div class="tab-pane fade show active" id="per_details_tab">
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title d-flex justify-content-between">
                                                        <span>Personal Details</span>
                                                        <a class="edit-link" data-toggle="modal" href="#edit_personal_details"><i class="far fa-edit mr-1"></i>Edit</a>
                                                    </h5>
                                                    <div class="row">
                                                        <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Name</p>
                                                        <p class="col-sm-9">${sessionScope.account.full_name}</p>
                                                    </div>
                                                    <div class="row">
                                                        <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Email ID</p>
                                                        <p class="col-sm-9">${sessionScope.account.email}</p>
                                                    </div>
                                                    <div class="row">
                                                        <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Mobile</p>
                                                        <p class="col-sm-9">${sessionScope.account.mobile}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
    <!-- Mirrored from preschool.dreamguystech.com/html-template/profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:39 GMT -->
</html>