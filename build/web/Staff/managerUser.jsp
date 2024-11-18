<%-- 
    Document   : managerUser
    Created on : Jul 5, 2024, 1:06:06 AM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerAdmin.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">User Management</h1>
            <a href="manager-user?action=new" class="btn btn-primary mb-4">Add New User</a>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <c:if test="${sessionScope.admin != null && sessionScope.admin.userId != user.userId}">
                            <tr>
                                <td>${user.userId}</td>
                                <td>${user.userName}</td>
                                <td>${user.email}</td>
                                <td>${user.phone}</td>
                                <td>${user.role}</td>
                                <td>
                                    <span class="badge
                                          ${user.status == 1 ? 'badge-success' : 'badge-danger'}">
                                        ${user.status == 1 ? 'Active' : 'Inactive'}
                                    </span>
                                </td>
                                <td>
                                    <a href="manager-user?action=view&userId=${user.userId}" class="btn btn-info btn-sm">View</a>
                                    <a href="manager-user?action=edit&userId=${user.userId}" class="btn btn-warning btn-sm">Edit</a>
                                    
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
