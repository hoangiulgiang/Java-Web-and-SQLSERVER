<%-- 
    Document   : list
    Created on : Jul 4, 2024, 2:15:03 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Manage Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container">
            <h1>Manage Menu</h1>
            <a href="weeklyMenu?action=add" class="btn btn-success mb-2">Add New Menu</a>
            <table class="table" id="data-table">
                <thead>
                    <tr>
                        <th>Menu Name</th>
                        <th>Description</th>
                        <th>Diet Type</th>
                        <th>Price</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="menu" items="${menus}">
                        <tr>
                            <td>${menu.menuName}</td>
                            <td>${menu.description}</td>
                            <td>${menu.dietType}</td>
                            <td>
                                <span class="badge badge-secondary price">
                                    ${menu.price}
                                </span>
                            </td>
                            <td>${menu.formatedDate}</td>
                            <td>
                                <a href="weeklyMenu?action=edit&menuId=${menu.menuId}" class="btn btn-warning">Edit</a>
                                <form action="weeklyMenu" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="deleteMenu">
                                    <input type="hidden" name="menuId" value="${menu.menuId}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            function formatCurrency(amount) {
                return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
            }
            document.addEventListener('DOMContentLoaded', function () {
                document.querySelectorAll('.price').forEach(function (element) {
                    var price = parseFloat(element.innerText);
                    element.innerText = formatCurrency(price);
                });
            });
        </script>
    </body>
</html>
