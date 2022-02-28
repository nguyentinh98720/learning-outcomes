<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set scope="page" var="cart" value="${sessionScope.cart}"/>

<c:set scope="page" var="numProduct" value="${cart.getItems().size()}"/>

<div class="cartdetail">

<c:if test="${numProduct == null || numProduct == 0}">
    <!-- the empty cart -->
    <div class="nothinghere">
        Bạn chưa có sản phẩm nào trong giỏ hàng
        <a href="list">Tiếp tục mua sắm</a>
        <hr>
        Hoặc
        <a href="#" onclick="comebacksoon()">Đăng nhập để xem lịch sử mua hàng</a>
    </div>
</c:if>
<c:if test="${numProduct > 0}">
    <table>
        <tr>
            <th>Số mặt hàng: <span><c:out value="${numProduct}"/></span></th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
            <th> </th>
        </tr>
<c:set scope="page" var="listProduct" value="${cart.getItems()}"/>
<c:forEach var="product" items="${listProduct}">
        <tr class="productArea">
            <td><strong><c:out value="${product.getName()}"/></strong></td>
            <td><c:out value="${product.getPrice()}"/>$</td>
            <td><c:out value="${product.getNumber()}"/></td>
            <td><fmt:formatNumber value="${product.getPrice() * product.getNumber()}" maxFractionDigits="2"/>$</td>
            <td class="deleteProduct"><a href="cart?action=delete&id=${product.getId()}">Loại bỏ</a></td>
        </tr>
</c:forEach>
        <tr>
            <th class="total" colspan="5">Tổng hóa đơn: <c:out value="${cart.getAmount()}"/>$</th>
        </tr>
    </table>
    <div id="userAction">
        <a href="list">Tiếp tục mua sắm</a>
        <p id="makeorder">Đặt hàng</p>
    </div>
        <!-- for order information -->
    <form action="/PRJ321x_A3/PayController" method="post" onsubmit="return makeorder()" id="orderinfo">
        <div class="input">
            <p id="notename">Tên hoặc email:</p>
            <input id="inputname" type="text" name="username"/>
        </div>
        <div class="input">
            <p id="noteaddress">Địa chỉ:</p>
            <input type="text" name="useraddress" id="inputaddress"/>
        </div>
        <div class="input">
            <p>Mã giảm giá (nếu có):</p>
            <input type="text" name="discountcode"/>
        </div>
        <div class="orderAction">
            <input type="submit" value="Gửi yêu cầu" id="submitorder" onclick="makeorder()"/>
        </div>
    </form>
</c:if>
</div>