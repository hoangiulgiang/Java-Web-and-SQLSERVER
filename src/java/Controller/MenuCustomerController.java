/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.WeeklyMenuDAO;
import Model.Recipe;
import Model.WeeklyMenu;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "MenuCustomerController", urlPatterns = {"/menu"})
public class MenuCustomerController extends HttpServlet {

    private WeeklyMenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new WeeklyMenuDAO();
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        try {
            switch (action) {
                case "viewDetails":
                    viewDetails(request, response);
                    break;
                case "searchMenu":
                    searchMenu(request, response);
                    break;
                default:
                    viewMenus(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }

    private String formatDateWithDayOfWeek(java.sql.Date date) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        date = date == null ? currentDate : date;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.ENGLISH);
        return sdf.format(date);
    }

    private void searchMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchQuery = request.getParameter("searchQuery");
            List<WeeklyMenu> menus = menuDAO.searchMenus(searchQuery);
            for (WeeklyMenu menu : menus) {
                List<Recipe> recipes = menuDAO.getRecipesForMenu(menu.getMenuId());
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
                menu.setRecipe(recipes);
            }
            request.setAttribute("menus", menus);
            request.setAttribute("searchQuery", searchQuery);
            request.getRequestDispatcher("/Customer/menus.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("menu?error=Search is not valid");
            System.out.println("Search menu: " + e);
        }
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            WeeklyMenu menu = menuDAO.getMenuByIdCustomer(menuId);
            if (menu != null) {
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
                request.setAttribute("menu", menu);
                List<Recipe> recipes = menuDAO.getRecipesForMenu(menu.getMenuId());
                menu.setRecipe(recipes);
                request.getRequestDispatcher("/Customer/menuDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("menu?error=Menu can not found");
            }
        } catch (Exception e) {
            response.sendRedirect("menu?error=Id is not valid");
        }
    }

    private void viewMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<WeeklyMenu> menus = menuDAO.getAllMenusCustomer();
            for (WeeklyMenu menu : menus) {
                List<Recipe> recipes = menuDAO.getRecipesForMenu(menu.getMenuId());
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
                menu.setRecipe(recipes);
            }
            request.setAttribute("menus", menus);
            request.getRequestDispatcher("/Customer/menus.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
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
