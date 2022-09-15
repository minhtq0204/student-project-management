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
                                <h3 class="page-title">Detail Feature</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="FeatureList">Feature</a></li>
                                    <li class="breadcrumb-item active">Detail Feature</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <h5 class="form-title"><span>Detail Feature</span></h5>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Feature ID : </label>
                                                <input type="text" class="form-control" name="featureId" value="${f.featureId}" readonly>
                                                <div class="error-code" id="error-code">${requestScope.errorcode}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Feature Name : </label>
                                                <input type="text" class="form-control" name="FeatureName" value="${f.featureName}"  readonly>
                                                <div class="error-name" id="error-name">${requestScope.errorname}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Team ID : </label>
                                                <input type="text" class="form-control" name="teamId" value="${f.team_id}"  readonly>
                                                <div class="error-name" id="error-name">${requestScope.errorname}
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Topic code : </label>
                                                <input type="text" class="form-control" name="teamId" value="${f.topicCode}" readonly>
                                                <div class="error-name" id="error-name">${requestScope.errorname}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Topic name: </label>
                                                <input type="text" class="form-control" name="teamId" value="${f.topicName}" readonly>
                                                <div class="error-name" id="error-name">${requestScope.errorname}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Gitlab Url : </label>
                                                <input type="text" class="form-control" name="teamId" value="${f.gitlabUrl}" readonly>
                                                <div class="error-name" id="error-name">${requestScope.errorname}
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


        <script src="css/jquery-3.6.0.min.js"></script>

        <script src="css/popper.min.js"></script>
        <script src="css/bootstrap.min.js"></script>

        <script src="css/jquery.slimscroll.min.js"></script>

        <script src="css/script.js"></script>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/add-subject.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:50 GMT 
</html>