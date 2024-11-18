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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet("/mealPlans") 

public class PersonalMealPlanServlet extends HttpServlet {

    private PersonalMealPlanDAO mealPlanDAO;
    private MealPlanDetailsDAO mealPlanDetailsDAO;

    @Override
    public void init() throws ServletException {
        mealPlanDAO = new PersonalMealPlanDAO();
        mealPlanDetailsDAO = new MealPlanDetailsDAO();
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
                case "viewPlans":
                    viewPlans(request, response);
                    break;
                case "add":
                    request.getRequestDispatcher("/Customer/createMealPlan.jsp").forward(request, response);
                    break;
                case "editPlan":
                    showEditForm(request, response);
                    break;
                case "deletePlan":
                    deletePlan(request, response);
                    break;
                default:
                    viewPlans(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "createPlan":
                    createPlan(request, response);
                    break;
                case "updatePlan":
                    updatePlan(request, response);
                    break;
                default:
                    viewPlans(request, response);
                    break;
            }
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error");
        }
    }

    private void viewPlans(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            List<PersonalMealPlan> plans = mealPlanDAO.getPlansByUserId(userId);
            request.setAttribute("plans", plans);
            request.getRequestDispatcher("/Customer/personalMealPlans.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error with data");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int planId = Integer.parseInt(request.getParameter("planId"));
            PersonalMealPlan plan = mealPlanDAO.getPlanById(planId);
            List<MealPlanDetails> details = mealPlanDetailsDAO.getDetailsByPlanId(planId);
            if (plan != null) {
                WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
                for (MealPlanDetails detail : details) {
                    WeeklyMenu menu = weeklyMenuDao.getMenuById(detail.getMealId());
                    detail.setMenu(menu);
                }
                request.setAttribute("plan", plan);
                request.setAttribute("details", details);
                request.getRequestDispatcher("/Customer/editMealPlan.jsp").forward(request, response);
            } else {
                response.sendRedirect("mealPlans?error=Plan is not exist");
            }
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error with data");
        }
    }

    private void createPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            String planName = request.getParameter("planName");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            int weeks = Integer.parseInt(request.getParameter("weeks"));

            PersonalMealPlan plan = new PersonalMealPlan();
            plan.setUserId(userId);
            plan.setPlanName(planName);
            plan.setStartDate(startDate);
            plan.setWeeks(weeks);

            if (mealPlanDAO.planExists(startDate, userId) != null) {
                response.getWriter().write("A plan already exists for the selected start date.");
                response.sendRedirect("mealPlans?error=A plan already exists for the selected start date.");
            } else {
                mealPlanDAO.addPlan(plan);
                response.sendRedirect("mealPlans?action=viewPlans");
            }
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error with data");
        }
    }

    private void updatePlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int planId = Integer.parseInt(request.getParameter("planId"));
            String planName = request.getParameter("planName");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            int weeks = Integer.parseInt(request.getParameter("weeks"));

            PersonalMealPlan plan = new PersonalMealPlan();
            plan.setPlanId(planId);
            plan.setPlanName(planName);
            plan.setStartDate(startDate);
            plan.setWeeks(weeks);

            mealPlanDAO.updatePlan(plan);
            response.sendRedirect("mealPlans?action=viewPlans");
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error with data");
        }
    }

    private void deletePlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int planId = Integer.parseInt(request.getParameter("planId"));
            mealPlanDAO.deletePlan(planId);
            response.sendRedirect("mealPlans?action=viewPlans");
        } catch (Exception e) {
            response.sendRedirect("mealPlans?error=Have a error with data");
        }
    }

}
