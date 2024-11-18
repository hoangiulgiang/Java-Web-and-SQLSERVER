


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : personal
    Created on : Jul 4, 2024, 5:19:07 PM
    Author     : duong minh hoang
--%>

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
            <h2 class="mt-4">Change Password</h2>
            <c:if test="${error != null}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="personalInfo" method="post">
                <input type="hidden" name="action" value="changePassword">
                <input type="hidden" name="userId" value="${user.userId}">
                <div class="form-group">
                    <label for="currentPassword">Current Password</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm New Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <button type="submit" class="btn btn-warning">Change Password</button>
            </form>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

