<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/maincss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">

<jsp:include page="header.jsp"/>

<%if(request.getAttribute("userSearch") == null && request.getAttribute("brand") == null && request.getAttribute("cart") == null && request.getAttribute("product") == null) { %>
	<jsp:include page="body.jsp"/>
<%} else if(request.getAttribute("cart") != null) { %>
	<jsp:include page="cart.jsp"/>
<%} else if(request.getAttribute("product") != null) { %>
	<jsp:include page="product.jsp"/>
<%} else if(request.getAttribute("brand") != null) { %>
	<jsp:include page="resultSearchBrand.jsp"/>
<%} else { %>
	<jsp:include page="resultSearch.jsp"/>
<%} %>

<jsp:include page="footer.jsp"/>

</div>

<script src="${pageContext.request.contextPath}/javaScript/mainjs.js" type="text/javaScript"></script>

</body>
</html>