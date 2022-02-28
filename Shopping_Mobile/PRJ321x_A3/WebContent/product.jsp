<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="page" var="product" value="${requestScope.product}"/>

<div class="showdetail">
    <div class="name"><h3><c:out value="${product.getName()}"/></h3></div>
    <div class="img"><img src="${product.getSrc()}" alt="${product.getName()}"/></div>
    <div class="detail">
        <div class="price"><h3><c:out value="${product.getPrice()}"/></h3>$</div>
        <div class="description"><p><c:out value="${product.getDescription()}"/></p></div>
    </div>
    <div class="userAction">
        <a href="buy?id=${product.getId()}" id="buynow">Mua ngay</a>
        <a href="cart?action=add&id=${product.getId()}" id="addtocart">Thêm vào giỏ hàng</a>
    </div>
</div>