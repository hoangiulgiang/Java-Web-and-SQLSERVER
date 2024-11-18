<%-- 
    Document   : editMealPlan
    Created on : Jul 4, 2024, 1:41:34 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Meal Plan</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Edit Meal Plan</h1>
            <form action="mealPlans" method="post"> 
                <input type="hidden" name="action" value="updatePlan">
                <input type="hidden" name="planId" value="${plan.planId}">
                <div class="form-group">
                    <label for="planName">Plan Name</label>
                    <input type="text" class="form-control" id="planName" name="planName" value="${plan.planName}" required>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input readonly type="date" class="form-control" id="startDate" name="startDate" value="${plan.startDate}" required>
                </div>
                <div class="form-group">
                    <label for="weeks">Number of Weeks</label>
                    <input type="number" class="form-control" id="weeks" name="weeks" min="1" max="4" value="${plan.weeks}" required>
                </div>
                <button type="submit" class="btn btn-warning">Update Plan</button>
            </form>

            <h2 class="mt-5">Meal Plan Details</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Day of Week</th>
                        <th>Meal</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${details}">
                        <tr>
                            <td>${detail.dayOfWeek}</td>
                            <td>${detail.menu.menuName}</td>
                            <td>
                                <form action="mealPlanDetail" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="deleteDetail">
                                    <input type="hidden" name="detailId" value="${detail.detailId}">
                                    <button onclick="return confirm('Are you sure to delete?')" type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="mealPlanDetail?action=add&planId=${plan.planId}" class="btn btn-success">Add Meal to Plan</a>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
