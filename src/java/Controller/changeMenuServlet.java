/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.PersonalMealPlanDAO;
import DAO.WeeklyMenuDAO;
import Model.PersonalMealPlan;
import Model.WeeklyMenu;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "changeMenuServlet", urlPatterns = {"/changeMenuServlet"})
public class changeMenuServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changeMenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeMenuServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        String selectedDayOfWeek = request.getParameter("selectedDay");

        if (planId == null || selectedDayOfWeek == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing planId or selectedDay parameter");
            return;
        }

        try {
            int id = Integer.parseInt(planId);

            PersonalMealPlanDAO planDao = new PersonalMealPlanDAO();
            PersonalMealPlan plan = planDao.getPlanById(id);

            if (plan == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Personal meal plan not found for id: " + id);
                return;
            }

            int selectedDayIndex = getDayIndex(selectedDayOfWeek);
            if (selectedDayIndex == -1) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid selectedDayOfWeek: " + selectedDayOfWeek);
                return;
            }

            Date selectedDate = calculateSelectedDate(plan.getStartDate(), selectedDayIndex);

            WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
            System.out.println(new java.sql.Date(selectedDate.getTime()));
            List<WeeklyMenu> weeklyMenu = weeklyMenuDao.getAllMenusByDateCorrect(new java.sql.Date(selectedDate.getTime()));

            Gson gson = new Gson();
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("weeklyMenu", gson.toJsonTree(weeklyMenu));

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid planId: " + planId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error processing request: " + e.getMessage());
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

    private Date calculateSelectedDate(Date startDate, int selectedDayIndex) {
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
