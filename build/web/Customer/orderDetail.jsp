<%-- 
    Document   : orderDetail
    Created on : Jul 4, 2024, 7:51:16 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order Details</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <div class="col-md-12 mb-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title">${menu.menuName}</h5>
                    </div>
                    <div class="card-body">
                        <h1 class="mb-4">Order Details</h1>
                        <p>Order ID: ${order.orderId}</p>
                        <p>Order Date: ${order.orderDate}</p>
                        <p>Total Amount: ${order.totalAmount} VND</p>
                        <p>Address: ${order.address}</p>
                        <p>Phone Number: ${order.phoneNumber}</p>
                        <p>Status: <span class="badge badge-danger">${order.status}</span></p>
                    </div>
                </div>
            </div>
            <h3>Order Items</h3>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Menu Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${orderItems}">
                        <tr>
                            <td>${item.menu.menuName}</td>
                            <td>${item.quantity}</td>
                            <td>${item.menu.price} VND</td>
                            <td>${item.quantity * item.menu.price} VND</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
