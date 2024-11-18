<%-- 
    Document   : editPersonal
    Created on : Jul 4, 2024, 5:30:02 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Personal Information</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Personal Information</h1>
            <c:if test="${error != null}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="personalInfo" method="post">
                <input type="hidden" name="action" value="updatePersonalInfo">
                <input type="hidden" name="userId" value="${user.userId}">
                <div class="form-group">
                    <label for="userName">Username</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" readonly>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" required>
                </div>
                <button type="submit" class="btn btn-primary">Update Personal Info</button>
            </form>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
