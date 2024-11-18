/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.MealPlanDetailsDAO;
import DAO.PersonalMealPlanDAO;
import DAO.WeeklyMenuDAO;
import Model.MealPlanDetails;
import Model.PersonalMealPlan;
import Model.User;
import Model.WeeklyMenu;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "MealPlanDetailsController", urlPatterns = {"/mealPlanDetail"})
public class MealPlanDetailsController extends HttpServlet {

    private MealPlanDetailsDAO mealPlanDetailsDAO;
    private WeeklyMenuDAO mealDAO;

    @Override
    public void init() throws ServletException {
        mealPlanDetailsDAO = new MealPlanDetailsDAO();
        mealDAO = new WeeklyMenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User uLogin = (User) session.getAttribute("user");
        if (uLogin == null || (uLogin != null && !uLogin.getRole().equals("user"))) {
            response.sendRedirect("login?error=You can not access to resourse");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        try {
            switch (action) {
                case "add":
                    this.addMealForm(request, response);
                    break;
                case "deleteDetail":
                    deleteDetail(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    private void addMealForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            String planId = request.getParameter("planId");
            WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
            PersonalMealPlanDAO planDao = new PersonalMealPlanDAO();
            PersonalMealPlan plan = planDao.getPlanById(Integer.parseInt(planId));
            if (plan != null) {
                List<String> allDaysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
                List<String> availableDayAdd = new ArrayList<>();
                for (String availableDay : allDaysOfWeek) {
                    if (!mealPlanDetailsDAO.isDayAlreadyAdded(Integer.parseInt(planId), availableDay)) {
                        availableDayAdd.add(availableDay);
                    }
                }
                int selectedDayIndex = getDayIndex(availableDayAdd.get(0));
                if (selectedDayIndex == -1) {
                    response.sendRedirect("mealPlans?planId=" + planId + "&error=You set for full week");
                    return;
                }
                Date selectedDate = calculateSelectedDate(plan.getStartDate(), selectedDayIndex);
                List<WeeklyMenu> weeklyMenu = weeklyMenuDao.getAllMenusByDateCorrect(new java.sql.Date(selectedDate.getTime()));
                request.setAttribute("weeklyMenu", weeklyMenu);
                request.setAttribute("planId", planId);
                request.setAttribute("availableDays", availableDayAdd);
                request.getRequestDispatcher("/Customer/addMealToPlan.jsp").forward(request, response);
            } else {
                response.sendRedirect("mealPlans?error=Can not found plan");
            }

        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action != null ? action : "";
        try {
            switch (action) {
                case "addDetail":
                    addDetail(request, response);
                    break;
                case "deleteDetail":
                    deleteDetail(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    private void addDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String dayOfWeek = request.getParameter("dayOfWeek");
        int mealId = Integer.parseInt(request.getParameter("mealId"));

        if (mealPlanDetailsDAO.isDayAlreadyAdded(planId, dayOfWeek)) {
            request.setAttribute("errorMessage", "This day is already added.");
            doGet(request, response);
        } else {
            MealPlanDetails meal = new MealPlanDetails(0, planId, dayOfWeek, mealId);
            mealPlanDetailsDAO.addDetail(meal);
            response.sendRedirect("mealPlans?action=editPlan&planId=" + planId);
        }
    }

    private void deleteDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int detailId = Integer.parseInt(request.getParameter("detailId"));
        MealPlanDetails detail = mealPlanDetailsDAO.getDetailById(detailId);
        try {
            mealPlanDetailsDAO.deleteDetail(detailId);
            response.sendRedirect("mealPlans?action=editPlan&planId=" + detail.getPlanId());
        } catch (Exception e) {
            response.sendRedirect("mealPlans?action=editPlan&planId=" + detail.getPlanId() + "&error=Have a error");
        }

    }

    private int getDayIndex(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase()) {
            case "sunday":
                return Calendar.SUNDAY;
            case "monday":
                return Calendar.MONDAY;
            case "tuesday":
                return Calendar.TUESDAY;
            case "wednesday":
                return Calendar.WEDNESDAY;
            case "thursday":
                return Calendar.THURSDAY;
            case "friday":
                return Calendar.FRIDAY;
            case "saturday":
                return Calendar.SATURDAY;
            default:
                return -1;
        }
    }

    private java.util.Date calculateSelectedDate(Date startDate, int selectedDayIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        int daysToAdd = selectedDayIndex - startDayOfWeek;

        if (daysToAdd < 0) {
            daysToAdd += 7;
        }
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return calendar.getTime();
    }

}
