<%-- 
    Document   : login
    Created on : May 16, 2022, 8:54:34 PM
    Author     : Thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styleLogin.css">
        <title>Login</title>
        <script>
            function validateform() {
                var password = document.myform.psw.value;
                } else if (password.length < 6) {
                    alert("Tài khoản cần > 4 ký tự");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%
            String unameck="";
            String passck="";
            String rememberck="";
            Cookie[] cookie = request.getCookies();
            if(cookie!=null){
                for(Cookie c :cookie){
                    if(c.getName().equals("cookieUsername")){
                        unameck= c.getValue();
                    }else if(c.getName().equals("cookiePassword")){
                        passck= c.getValue();
                    }
                    
                }
            }
        %>
         <c:choose>
                <c:when test="${stt.equals('1')}">
                    <div class="position-fixed bottom-0 end-0 p-3" style="right: 10px; bottom: 10px; z-index: 11">
                        <div class="toast" data-autohide="true">
                            <div class="toast-header bg-success">
                                <strong class="mr-auto text-white"><h4>Register Successfully</h4></strong>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                            </div>
                            <div class="toast-body">
                                Register Successfully.
                            </div>
                        </div>
                    </div>
                </c:when>
             </c:choose>
        <div id="login-form-wrap">
            <h2>ĐĂNG NHẬP VÀO TRANG</h2>
            <a>Sử dụng tài khoản đã được đăng ký để đăng nhập vào trang web.</a>
            <form id="login-form" name="myform" action="LoginServlet" method="post" onsubmit="return validateform()" >
                <a style="color: red">${message != null ? message : ""}</a>
                <p>
                    <input type="text" id="username" name="uname" placeholder="Username" value="${oldUname != null ? oldUname : unameck}" required>
                </p>
                <p>
                    <input type="password" id="password" name="psw" placeholder="Password" value="${oldPass != null ? oldPass : passck}" required>
                </p>
                <input type="checkbox" id="remember" name="rememberMe" value="1"> <a>Ghi nhớ tài khoản</a>
                <p>
                    <input type="submit" id="loginButton" value="Login">
                </p>
            </form>
            <div id="create-account-wrap">
                <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a><p>
            </div><!--create-account-wrap-->
        </div><!--login-form-wrap-->
    </body>
</html>
