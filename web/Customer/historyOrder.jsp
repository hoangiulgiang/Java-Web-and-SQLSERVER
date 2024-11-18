<%-- 
    Document   : historyOrder
    Created on : Jul 4, 2024, 7:50:03 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order History</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Order History</h1>
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
            <c:forEach var="order" items="${orderHistory}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Order ID: ${order.orderId}</h5>
                        <p class="card-text">Date: ${order.orderDate}</p>
                        <p class="card-text">Total Amount: ${order.totalAmount} VND</p>
                        <p class="card-text">Address: ${order.address}</p>
                        <p class="card-text">Phone Number: ${order.phoneNumber}</p>
                        <p class="card-text">Status:  <span class="badge 
                                  ${order.status == 'Pending' ? 'badge-warning' : ''}                                 
                                  ${order.status == 'Completed' ? 'badge-primary' : ''}
                                  ${order.status == 'Cancelled' ? 'badge-danger' : ''}">
                                ${order.status}
                            </span></p>
                        <a href="orderHistory?action=view&orderId=${order.orderId}" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${orderHistory.size() == 0}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">No have order</h5>
                    </div>
                </div>
            </c:if>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
