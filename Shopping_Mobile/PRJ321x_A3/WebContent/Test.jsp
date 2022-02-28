<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Testing here</h2>
<c:forEach var="product" items="${listProduct}">
		        <%-- <div class="col-lg-4 product">
		            <div class="image"><img src="${pageContext.request.contextPath}/media/${product.getName()}-${product.getId()}"/></div>
		            <div class="type"><p><c:out value='${product.getType()}'></c:out></p></div>
		            <div class="name"><a href="#"><c:out value='${product.getName() }'></c:out></a></div>
		            <div class="price"><p><c:out value='${product.getPrice() }'></c:out>$</p></div>
		            <div class="userAction">
		                <a href="#">Thêm vào giỏ hàng</a>
		                <a href="#">Mua ngay</a>
		            </div>
		        </div> --%>
    		</c:forEach>
</body>
</html>