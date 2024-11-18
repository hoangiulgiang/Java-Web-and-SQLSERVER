<%-- 
    Document   : list
    Created on : Jul 4, 2024, 2:21:42 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipes</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@include file="../components/headerAdmin.jsp" %>
<div class="container">
    <h1>Recipes</h1>
    <a href="recipes?action=add" class="btn btn-primary mb-2">Add New Recipe</a>
    <table class="table"  id="data-table">
        <thead>
            <tr>
                <th>Recipe ID</th>
                <th>Menu Name</th>
                <th>Recipe Name</th>
                <th>Ingredients</th>
                <th>Instructions</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>${recipe.recipeId}</td>
                    <td>${menuMap[recipe.menuId]}</td>
                    <td>${recipe.recipeName}</td>
                    <td>${recipe.ingredients}</td>
                    <td>${recipe.instructions}</td>
                    <td>
                        <a href="recipes?action=edit&recipeId=${recipe.recipeId}" class="btn btn-sm btn-primary">Edit</a>
                        <a href="recipes?action=delete&recipeId=${recipe.recipeId}" class="btn btn-sm btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
