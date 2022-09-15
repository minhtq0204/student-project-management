<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>User Register</title>

        <link rel="shortcut icon" href="imgage/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

        <link rel="stylesheet" href="css/bootstrap.min.css">

        <link rel="stylesheet" href="css/fontawesome.min.css">
        <link rel="stylesheet" href="css/all.min.css">

        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <div class="main-wrapper login-body">
            <div class="login-wrapper">
                <div class="container">
                    <div class="loginbox">
                        <div class="login-left">
                            <img class="img-fluid"  >
                        </div>
                        <div class="login-right">
                            <div class="login-right-wrap">
                                <h1>User Register</h1>
                                <p class="account-subtitle"></p>

                                <form id="registerForm" name="registerform" action="register" method="post"  >
                                    <div class="form-group">
                                       
                                        <input class="form-control" type="text" name="fullname" placeholder="Enter full name" value="${requestScope.fullname}" required="">
                                       
                                    </div>
                                    <div class="form-group">
                                        
                                        <input class="form-control" type="text" name="email" placeholder="Enter email" value="${requestScope.email}" required="">
                                         <div class="error-email" id="error-email">${requestScope.erroremail}</div>
                                        
                                    </div>
                                    <div class="form-group">
                                        
                                        <input class="form-control" type="password" name="pass" placeholder="Enter password" value="${requestScope.pass}" required="">
                                        <div class="error-pass" id="error-repass">${requestScope.errorpass}</div>
                                    </div>
                                    <div class="form-group">
                                      
                                        <input class="form-control" type="password" name="re-pass" placeholder="Enter re-pass" value="${requestScope.re-pass}" required="">
                                        <div class="error-repass" id="error-repass">${requestScope.errorrepass}</div>

                                    </div>
                                    <div class="form-group">
                                        <label for="gender"><b>Gender</b></label>
                                        <br>
                                        <input type="radio" checked="" name="gender" value="0">Male
                                        <input type="radio" name="gender" value="1">Female
                                    </div>
                                    <div class="form-group">
                                       
                                        <input class="form-control" type="text" name="phone" placeholder="Enter phone " value="${requestScope.phone}">
                                        <div class="error-phone" id="error-phone">${requestScope.errorphone}</div>
                                    </div>

                                    <div class="form-group mb-0">
                                        <button class="btn btn-primary btn-block" type="submit">Register</button>
                                    </div>
                                </form>

                                <div class="login-or">
                                    <span class="or-line"></span>
                                    <span class="span-or">or</span>
                                </div>

                                <div class="text-center dont-have">Already have an account? <a href="login.jsp">Login</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="css/jquery-3.6.0.min.js"></script>

        <script src="css/popper.min.js"></script>
        <script src="css/bootstrap.min.js"></script>
        <script src="css/script.js"></script>
        
       
        
    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
</html>