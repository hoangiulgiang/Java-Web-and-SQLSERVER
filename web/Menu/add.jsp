<%-- 
    Document   : add
    Created on : Jul 4, 2024, 2:14:46 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add Menu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container">
            <h1>Add Menu</h1>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="weeklyMenu" method="post">
                <input type="hidden" name="action" value="addMenu">
                <div class="form-group">
                    <label for="menuName">Menu Name</label>
                    <input type="text" class="form-control" name="menuName" id="menuName" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" name="description" id="description" rows="3" required></textarea>
                </div>
                <div class="form-group">
                    <label for="dietType">Diet Type</label>
                    <select class="form-control" name="dietType" id="dietType">
                        <option value="vegetarian">Vegetarian</option>
                        <option value="vegan">Vegan</option>
                        <option value="special">Special</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="date">Date</label>
                    <input type="date" class="form-control" name="date" id="date" required>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input min="0" type="number" class="form-control" name="price" id="date" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Menu</button>
            </form>
        </div>
    </body>
</html>
