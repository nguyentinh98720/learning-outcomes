<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logincss.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
    <script src="${pageContext.request.contextPath}/javaScript/loginjs.js" type="text/javascript"></script>
</head>
<body>
<%
String error = (String)request.getAttribute("error");
Cookie cookie = null;
Cookie[] cookies = null;
String email = null;
String password = null;
cookies = request.getCookies();
if(cookies == null) { response.getWriter().println("no cookie."); } else {
	for(Cookie arrCookie : cookies) {
		if(arrCookie.getName().equals("email")){
			email = arrCookie.getValue();
		}
		if(arrCookie.getName().equals("passw")){
			password = arrCookie.getValue();
		}
	}
}
%>
    <div class="container">
        <div class="row">
            <div class="col okhide"></div>
            <div class="col-lg-6 dvcenter">
                <div class="row dvmiddle">
                    <div class="col-lg-5 welcome">
                        <span><% if (error != null) { %><%= error %><% } %></span>
                        <img src="${pageContext.request.contextPath}/media/user.png" alt="Hình đăng nhập" id="loginimg">
                    </div>
                    <div class="col-lg-7 dvform">
                        <form action="login" method="post" onsubmit="return valform()">
                            <p id="forname">Tên đăng nhập</p>
                            <input type="text" name="username" id="username" placeholder="Enter username" value="<%if (email != null) {%><%= email %><%}%>" onfocus="writename()" onblur="writedname()"/>
                            <p id="forpass">Mật khẩu</p>
                            <input type="password" name="userpass" id="userpass" placeholder="Enter password" value="<%if (password != null) {%><%= password %><%}%>" onfocus="writepass()" onblur="writedpass()"/>
                            <hr>
                            <input type="checkbox" name="remuser" id="rememberpass"/>
                            <span class="rememberpass">Nhớ mật khẩu</span>
                            <hr>
                            <input type="submit" value="Đăng nhập" id="submit" onclick="valform()">
                        </form>
                        <p class="forgotpass">Bạn <a href="#" onclick="comebacksoon()">quên</a> mật khẩu?</p>
                        
                        <a href="login?action=logout"><button type="button" id="cancel">Trang chủ</button></a>
                    </div>
                </div>
            </div>
            <div class="col okhide"></div>
        </div>
    </div>
</body>
</html>