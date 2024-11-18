<%-- 
    Document   : edit
    Created on : Jul 4, 2024, 2:21:51 AM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Recipe</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container">
            <h1>Edit Recipe</h1>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="recipes?action=edit" method="POST">
                <input type="hidden" name="recipeId" value="${recipe.recipeId}" />
                <div class="form-group">
                    <label for="menuId">Menu:</label>
                    <select class="form-control" id="menuId" name="menuId" required>
                        <option value="">Select Menu</option>
                        <c:forEach var="menu" items="${menus}">
                            <option value="${menu.menuId}" <c:if test="${menu.menuId == recipe.menuId}">selected</c:if>>${menu.menuName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="recipeName">Recipe Name:</label>
                    <input type="text" class="form-control" id="recipeName" name="recipeName" value="${recipe.recipeName}" required>
                </div>
                <div class="form-group">
                    <label for="ingredients">Ingredients:</label>
                    <textarea class="form-control" id="ingredients" name="ingredients" rows="3" required>${recipe.ingredients}</textarea>
                </div>
                <div class="form-group">
                    <label for="instructions">Instructions:</label>
                    <textarea class="form-control" id="instructions" name="instructions" rows="3" required>${recipe.instructions}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">Update Recipe</button>
                <a href="recipes" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
