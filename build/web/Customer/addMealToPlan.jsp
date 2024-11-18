<%-- 
    Document   : addMealToPlan
    Created on : Jul 4, 2024, 1:41:52 PM
    Author     : duong minh hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add Meal to Plan</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="../components/headerCustomer.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Add Meal to Plan</h1>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>
            <form action="mealPlanDetail" method="post">
                <input type="hidden" name="action" value="addDetail">
                <input type="hidden" name="planId" value="${planId}">
                <div class="form-group">
                    <label for="dayOfWeek">Day of Week</label>
                    <select onchange="changeMenu('${planId}', this.value)" class="form-control" id="dayOfWeek" name="dayOfWeek">
                        <c:forEach var="day" items="${availableDays}">
                            <option value="${day}">${day}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="mealId">Meal</label>
                    <select class="form-control" id="mealId" name="mealId">
                        <c:forEach var="meal" items="${weeklyMenu}">
                            <option value="${meal.menuId}">${meal.menuName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button ${weeklyMenu.size() == 0 ? "disabled" : ""} type="submit" class="btn btn-success" id="addMealButton"> ${weeklyMenu.size() == 0 ? "No Menu Available" : "Add"}</button>
            </form>
        </div>
        <%@include file="../components/footerCustomer.jsp" %>
        <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                        function changeMenu(planId, selectedDay) {
                            $.ajax({
                                type: "POST",
                                url: "changeMenuServlet",
                                data: {
                                    planId: planId,
                                    selectedDay: selectedDay
                                },
                                dataType: "json",
                                success: function (response) {
                                    if (response && response.weeklyMenu && response.weeklyMenu.length > 0) {
                                        var menuOptions = "";
                                        $.each(response.weeklyMenu, function (index, menu) {
                                            menuOptions += "<option value='" + menu.menuId + "'>" + menu.menuName + "</option>";
                                        });
                                        $("#mealId").html(menuOptions);

                                        $("#addMealButton").text("Add Meal");
                                        $("#addMealButton").prop("disabled", false);
                                    } else {
                                        $("#mealId").empty();
                                        $("#mealId").append("<option value=''>No Menu Available</option>");

                                        $("#addMealButton").text("No Menu Available");
                                        $("#addMealButton").prop("disabled", true);
                                    }
                                },
                                error: function (xhr, status, error) {
                                    console.log("Error: " + error);
                                }
                            });
                        }
        </script>
    </body>
</html>
