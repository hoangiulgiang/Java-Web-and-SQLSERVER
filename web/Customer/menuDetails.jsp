<%-- 
    Document   : menuDetails
    Created on : Jul 4, 2024, 1:13:59 PM
    Author     : duong minh hoang
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Menu Details</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Menu Details</h1>
            <form action="menu" method="get" class="form-inline mb-4">
                <input type="hidden" name="action" value="searchMenu">
                <input type="text" name="searchQuery" class="form-control mr-2" placeholder="Search by menu name" value="${searchQuery}">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title">${menu.menuName}</h5>
                </div>
                <div class="card-body">
                    <p><strong>Description:</strong> ${menu.description}</p>
                    <p><strong>Diet Type:</strong> ${menu.dietType}</p>
                    <p><strong>Date:</strong> <span class="badge badge-success">${menu.formatedDate}</span></p>
                    <p><strong>Price:</strong> <span class="btn btn-secondary price-vnd">${menu.price}</span></p>
                    <h6>Recipes:</h6>
                    <ul class="list-group">
                        <c:forEach var="recipe" items="${menu.recipe}">
                            <li class="list-group-item">
                                <h6>${recipe.recipeName}</h6>
                                <p><strong>Ingredients:</strong> ${recipe.ingredients}</p>
                                <p><strong>Instructions:</strong> ${recipe.instructions}</p>
                            </li>
                        </c:forEach>
                        <c:if test="${menu.recipe.size() == 0}">
                            <li class="list-group-item">
                                <div class="alert alert-info" role="alert">
                                    <h6>No have recipes</h6>
                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <div class="card-footer text-right">
                    <a href="menu" class="btn btn-secondary">Back to Menus</a>
                    <a href="order?menuId=${menu.menuId}" class="btn btn-primary">Order This Menu</a>
                </div>
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
