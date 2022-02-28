<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="page" var="cart" value="${sessionScope.cart}"/>

<c:set scope="page" var="numProduct" value="${cart.getNumberOfProduct()}"/>


<!-- Logo and searching -->
<div class="row header">
    <div class="col-lg-3 logo">
        <h5>PRJ321_A2</h5>
        <p>Welcome to my website</p>
    </div>
    <div class="col-lg-9">
        <form action="search" onsubmit="return searchsomething()">
            <input type="submit" id="sbutton" onclick="searchsomething()" value="🔍"/>
            <input type="text" name="userSearch" id="sinput" placeholder="Tìm kiếm ở đây..." onblur="whenInput()"/>
        </form>
    </div>
</div>

<!-- Navigation -->
<div class="row topnav" onmouseleave="togglelist1()">
    <div class="col-lg-3 leftnav">
        <a href="/PRJ321x_A3/list">Trang chủ</a>
        <p onmouseenter="togglelist()">Sản phẩm</p>
        <a href="#" onclick="comebacksoon()">Shop</a>
    </div>
    <div id="productlist" onmouseleave="togglelist2()">
        <a href="search?brand=apple">Apple</a>
        <a href="search?brand=ericsson">Ericsson</a>
        <a href="search?brand=samsung">Samsung</a>
    </div>
    <div class="col-lg-4 rightnav">
        <div id="welcome"></div>
        <a href="cart?action=showcart" id="cart">
            <span class="cartlogo">🛒</span>
            <c:if test="${numProduct > 0}">
	            <span id="number"><c:out value="${numProduct}"/></span>
            </c:if>
        </a>
        <div id="account">
            <a href="login?action=login">Đăng nhập</a>
            <a href="#" onclick="comebacksoon()">Đăng ký</a>
        </div>
    </div>
</div>
