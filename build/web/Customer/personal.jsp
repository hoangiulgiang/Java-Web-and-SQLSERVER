<%-- 
    Document   : personal
    Created on : Jul 4, 2024, 5:19:07 PM
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
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${param.error}">
            <div class="alert alert-danger">${param.error}</div>
        </c:if>
        <c:if test="${param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <div class="container mt-5">
            <h1 class="mb-4">Personal Information</h1>
            <div>
                <input type="hidden" name="action" value="updatePersonalInfo">
                <div class="form-group">
                    <label for="userName">Username</label>
                    <input readonly type="text" class="form-control" id="userName" name="userName" value="${user.userName}" readonly>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input readonly type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input readonly type="text" class="form-control" id="phone" name="phone" value="${user.phone}" required>
                </div>
                <a href="personalInfo?action=editProfile" class="btn btn-primary">Update Personal Info</a>
                <a href="personalInfo?action=editPassword" class="btn btn-secondary">Update Password</a>
            </div>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
