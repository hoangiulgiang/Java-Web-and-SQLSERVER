<%-- 
    Document   : personalMealPlans
    Created on : Jul 4, 2024, 1:40:56 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Personal Meal Plans</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Personal Meal Plans</h1>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <c:if test="${param.success != null}">
                <div class="alert alert-success" role="alert">
                    ${param.success}
                </div>
            </c:if>
            <a href="mealPlans?action=add" class="btn btn-success mb-4">Create New Meal Plan</a>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Plan Name</th>
                        <th>Start Date</th>
                        <th>Weeks</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="plan" items="${plans}">
                        <tr>
                            <td>${plan.planName}</td>
                            <td>${plan.startDate}</td>
                            <td>${plan.weeks}</td>
                            <td>
                                <a href="mealPlans?action=editPlan&planId=${plan.planId}" class="btn btn-warning">Edit</a>
                                <form action="mealPlans" method="get" style="display:inline;">
                                    <input type="hidden" name="action" value="deletePlan">
                                    <input type="hidden" name="planId" value="${plan.planId}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
