<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="body">
    <div class="titleproduct">
        <p id="title">Tất cả sản phẩm</p>
    </div>
    
    <div class="row productArea">
			<c:forEach var="product" items="${listProduct}">
		        <div class="col-lg-4 product">
		            <a href="product?id=${product.getId()}" class="image"><img src="${product.getSrc()}" alt="${product.getName()}"/></a>
		            <div class="type"><p><c:out value='${product.getType()}'></c:out></p></div>
		            <div class="name"><a href="product?id=${product.getId()}"><c:out value='${product.getName() }'></c:out></a></div>
		            <div class="price"><p><c:out value='${product.getPrice() }'></c:out>$</p></div>
		            <div class="userAction">
		                <a href="cart?action=add&id=${product.getId()}">Thêm vào giỏ hàng</a>
		                <a href="buy?id=${product.getId()}">Mua ngay</a>
		            </div>
		        </div>
    		</c:forEach>
    </div>
    <div class="page">
<%
int from = 1;
if(request.getAttribute("from") != null) {
	from = (Integer)request.getAttribute("from");
}
int totalProduct = 0;
if(request.getAttribute("numOfProduct") != null) {
	totalProduct = (Integer)request.getAttribute("numOfProduct");
}
int ITEMS_PER_PAGE = 9;
int numberOfPage = totalProduct/ITEMS_PER_PAGE + 1;
int prev = from - ITEMS_PER_PAGE;
int next = from + ITEMS_PER_PAGE;
if(prev > 0) {
	out.println("<a href='list?from=" + prev + "&to=" + (prev + ITEMS_PER_PAGE - 1) + "'id='prev'>👈</a>");
}
for(int i = 1; i <= numberOfPage; i++) {
	if(from == (ITEMS_PER_PAGE *(i-1) + 1)) {
		out.println("<a href='list?from=" + (ITEMS_PER_PAGE * (i-1) + 1) + "&to=" + (ITEMS_PER_PAGE*i) + " ' id='numpageActive'>" + i + "</a>");
	} else {
		out.println("<a href='list?from=" + (ITEMS_PER_PAGE * (i-1) + 1) + "&to=" + (ITEMS_PER_PAGE*i) + " ' id='numpage'>" + i + "</a>");
	}
}
if(next <= totalProduct) {
	out.println("<a href='list?from=" + next + "&to=" + (next + ITEMS_PER_PAGE - 1) + "' id='next'>👉</a>");
}
%>
    </div>
</div>
