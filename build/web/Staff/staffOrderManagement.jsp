<%-- 
    Document   : StaffOrderManagement
    Created on : Jul 4, 2024, 9:23:17 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Staff Order Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Order Management</h1>
            <form action="manager-order" method="get" class="form-inline mb-4">
                <input type="hidden" class="form-control mr-2" name="action" value="search">
                <input value="${query}" type="text" class="form-control mr-2" name="query" placeholder="Search by date, phone, email">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <c:if test="${param.success != null}">
                <div class="alert alert-success" role="alert">
                    ${param.success}
                </div>
            </c:if>
            <c:forEach var="order" items="${orderList}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Order ID: ${order.orderId}</h5>
                        <p class="card-text">Date: ${order.orderDate}</p>
                        <p class="card-text">Total Amount: ${order.totalAmount} VND</p>
                        <p class="card-text">Address: ${order.address}</p>
                        <p class="card-text">Phone Number: ${order.phoneNumber}</p>
                        <p class="card-text">Status: 
                            <span class="badge 
                                  ${order.status == 'Pending' ? 'badge-warning' : ''}
                                  ${order.status == 'Confirmed' ? 'badge-info' : ''}
                                  ${order.status == 'Delivered' ? 'badge-success' : ''}
                                  ${order.status == 'Completed' ? 'badge-primary' : ''}
                                  ${order.status == 'Cancelled' ? 'badge-danger' : ''}">
                                ${order.status}
                            </span>
                        </p>
                        <a href="manager-order?action=view&orderId=${order.orderId}" class="btn btn-primary">View Details</a>
                        <c:if test="${order.status != 'Completed' && order.status != 'Cancelled'}">
                            <a onclick="return confirm('Are you sure to completed order?')" href="manager-order?action=update&orderId=${order.orderId}&status=Completed" class="btn btn-success">Mark as Completed</a>
                            <a onclick="return confirm('Are you sure to cancel order?')" href="manager-order?action=update&orderId=${order.orderId}&status=Cancelled" class="btn btn-danger">Cancel Order</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
