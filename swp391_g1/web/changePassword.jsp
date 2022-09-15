<%-- 
    Document   : changePassword
    Created on : May 28, 2022, 12:11:44 PM
    Author     : minhc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">-->
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="home__header">
            <%@ include file = "header.jsp" %>
        </div>
        <%@ include file = "header.jsp" %>
        <div>
            <div style="    padding: 200px 500px;
                 display: flex;
                 flex-direction: column;
                 align-items: stretch;
                 background-image: url(https://thienthumobile.vn/anh-nen-powerpoint-dep-full-hd/imager_4_45063_700.jpg);" class="card-body;">
                <h5 class="card-title">Change Password</h5>
                <div>
                    <div>
                        <form method="post" action="changePass" name="changePassword" onsubmit="success()">
                            <div class="form-group">
                                <label>Old Password</label>
                                <input name="oldPassword" id="oldPassword" type="password" class="form-control" onchange="checkOld();" required>
                            </div>
                            <div class="form-group">
                                <label>New Password</label>
                                <input name="password" id="password" type="password" class="form-control" onchange="CheckreOldPass();" required>
                            </div>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input name="Repassword" id="Repassword" type="password" class="form-control" onchange="checkRepass();" required>
                            </div>
                            <button id="submit" class="btn btn-primary" type="submit" >Save Changes</button>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <%@ include file = "footer.jsp" %>
            </div>
            
        </div>
     
    </body>
</html>

<script type="text/javascript">
//    function check_pass() {
//        if (document.getElementById('password').value ==
//                document.getElementById('Repassword').value) {
//            document.getElementById('submit').disabled = false;
//        } else {
//            document.getElementById('submit').disabled = true;
//        }
//    }


                                function checkOld()
                                {
                                    if (document.changePassword.oldPassword.value != ${sessionScope.account.password})
                                    {
                                        alert('Old password is incorrect');
                                        document.changePassword.NewPassword.focus(); //To focus the cursor in the old password text box
                                        return false;
                                    }
                                    document.changePassword.submit();
                                }

                                function checkRepass()
                                {
                                    if (document.changePassword.password.value == "")
                                    {
                                        alert('Please input confirm password');
                                        document.changePassword.Repassword.focus(); //to focus the cursor in the new password text box
                                        return false;
                                    }

                                    if (document.changePassword.password.value != document.changePassword.Repassword.value)
                                    {//checking whether the newpassword and confirm password entered by the user is a match or not
                                        alert('Confirm password does not match with the new password');
                                        document.changePassword.Repassword.focus(); //if doest not match focusing the cursor in the confirm pssword text box
                                        return false;
                                    }
                                }

                                function success()
                                {
                                    alert('Change password successfully !!!');
                                }
</script>
<script>
            $(document).ready(function () {
                $(".toast").toast({delay: 2000});
            $(".toast").toast("show");
            
            });
        </script>