<%-- 
    Document   : createMealPlan
    Created on : Jul 4, 2024, 1:41:17 PM
    Author     : duong minh hoang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create Meal Plan</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Create New Meal Plan</h1>
            <form action="mealPlans" method="post" id="createPlanForm" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="createPlan">
                <div class="form-group">
                    <label for="planName">Plan Name</label>
                    <input type="text" class="form-control" id="planName" name="planName" required>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                </div>
                <div class="form-group">
                    <label for="weeks">Number of Weeks</label>
                    <input type="number" class="form-control" id="weeks" name="weeks" min="1" max="4" required>
                </div>
                <button type="submit" class="btn btn-success">Create Plan</button>
            </form>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                // Hàm kiểm tra và cập nhật giá trị min cho input startDate
                function updateStartDateMin() {
                    var today = new Date();
                    var dd = String(today.getDate()).padStart(2, '0');
                    var mm = String(today.getMonth() + 1).padStart(2, '0');
                    var yyyy = today.getFullYear();

                    var minDate = yyyy + '-' + mm + '-' + dd;
                    document.getElementById("startDate").setAttribute("min", minDate);
                }

                $(document).ready(function () {
                    updateStartDateMin();
                });

                function validateForm() {
                    var startDateInput = document.getElementById("startDate");
                    var selectedDate = new Date(startDateInput.value);
                    var today = new Date();

                    if (selectedDate < today) {
                        alert("Please select a date that is not before today.");
                        return false;
                    }

                    var dayOfWeek = selectedDate.getDay();
                    if (dayOfWeek !== 1) {
                        alert("Please select a Monday as the start date.");
                        return false;
                    }

                    return true;
                }
        </script>
    </body>
</html>
