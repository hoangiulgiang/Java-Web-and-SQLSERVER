<%-- 
    Document   : menus
    Created on : Jul 4, 2024, 12:59:56 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Weekly Menu</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Weekly Menu</h1>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <form action="menu" method="get" class="form-inline mb-4">
                <input type="hidden" name="action" value="searchMenu">
                <input type="text" name="searchQuery" class="form-control mr-2" placeholder="Search by menu name" value="${searchQuery}">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <div class="row">
                <c:forEach var="menu" items="${menus}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">${menu.menuName}</h5>
                            </div>
                            <div class="card-body">
                                <p><strong>Description:</strong> ${menu.description}</p>
                                <p><strong>Diet Type:</strong> ${menu.dietType}</p>
                                <p><strong>Date:</strong> <span class="badge badge-success">${menu.formatedDate}</span></p>
                                <p><strong>Price:</strong> <span class="btn btn-secondary price-vnd">${menu.price}</span></p>
                            </div>
                            <div class="card-footer text-right">
                                <a href="menu?action=viewDetails&menuId=${menu.menuId}" class="btn btn-info">View Details</a>
                                <a href="order?menuId=${menu.menuId}" class="btn btn-primary">Order This Menu</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${menus.size() == 0}">
                    <div class="col-md-12 mb-12" style="text-align: center">
                        <img src="https://organickle.com/images/no-order.svg" width="50%"/>
                    </div>
                </c:if>
            </div>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.price-vnd').each(function () {
                    var price = parseFloat($(this).text());
                    if (!isNaN(price)) {
                        var formattedPrice = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(price);
                        $(this).text(formattedPrice);
                    }
                });
            });
        </script>
    </body>
</html>

