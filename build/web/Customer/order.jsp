<%-- 
    Document   : order
    Created on : Jul 4, 2024, 7:36:26 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order Meals</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${param.error}">
            <div class="alert alert-danger">${param.error}</div>
        </c:if>
        <c:if test="${param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <div class="container mt-5">
            <h1 class="mb-4">Order Meals</h1>
            <form action="order" method="post">
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
                </div>
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Menu ID</th>
                            <th>Menu Name</th>
                            <th>Price (VND)</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="meal" items="${mealList}">
                            <tr>
                                <td>
                                    <input type="hidden" name="menus" value="${meal.menuId}">
                                    ${meal.menuId}
                                </td>
                                <td>${meal.menuName}</td>
                                <td>${meal.price}</td>
                                <td>
                                    <input type="number" class="form-control" id="meal_${meal.menuId}" name="quantity_${meal.menuId}" min="1" value="1">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary">Place Order</button>
            </form>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

