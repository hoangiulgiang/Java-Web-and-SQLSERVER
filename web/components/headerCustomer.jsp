<%-- 
    Document   : headerAdmin
    Created on : Jul 4, 2024, 7:36:03 AM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap4.css"/>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
<script>
    $(document).ready(function () {
        $("#data-table").DataTable();
    });
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Healthy</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="menu">Menu</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="mealPlans">Personal meal</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="orderHistory">History order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="personalInfo">Profile</a>
                </li>
            </c:if>
        </ul>
        <c:if test="${sessionScope.user != null}">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="personalInfo">Hello, ${sessionScope.user.userName}</a>
                </li>
                <li class="nav-item">
                    <a onclick="return confirm('Are you sure to logout?')" class="nav-link" href="logout?action=user">Logout</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="login">Login</a>
                </li>
            </ul>
        </c:if>
    </div>
</nav>
