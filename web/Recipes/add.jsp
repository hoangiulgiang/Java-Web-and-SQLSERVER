<%-- 
    Document   : add
    Created on : Jul 4, 2024, 2:21:45 AM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Recipe</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container">
            <h1>Add Recipe</h1>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="recipes?action=add" method="POST">
                <div class="form-group">
                    <label for="menuId">Menu:</label>
                    <select class="form-control" id="menuId" name="menuId" required>
                        <option value="">Select Menu</option>
                        <c:forEach var="menu" items="${menus}">
                            <option value="${menu.menuId}" <c:if test="${menu.menuId == menuId}">selected</c:if>>${menu.menuName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="recipeName">Recipe Name:</label>
                    <input type="text" class="form-control" id="recipeName" name="recipeName" value="${recipeName}" required>
                </div>
                <div class="form-group">
                    <label for="ingredients">Ingredients:</label>
                    <textarea class="form-control" id="ingredients" name="ingredients" rows="3" required>${ingredients}</textarea>
                </div>
                <div class="form-group">
                    <label for="instructions">Instructions:</label>
                    <textarea class="form-control" id="instructions" name="instructions" rows="3" required>${instructions}</textarea>
                </div>
                <c:if test="${menus.size() == 0}">
                    <button disabled class="btn btn-secondary">Please add a menu before</button>
                </c:if>
                <c:if test="${menus.size() > 0}">
                    <button type="submit" class="btn btn-primary">Add Recipe</button>
                </c:if> 
                <a href="recipes" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
