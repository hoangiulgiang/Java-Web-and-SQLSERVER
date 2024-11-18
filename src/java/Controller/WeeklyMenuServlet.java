/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.WeeklyMenuDAO;
import Model.WeeklyMenu;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author duong minh hoang
 */
@WebServlet("/weeklyMenu")
public class WeeklyMenuServlet extends HttpServlet {

    private WeeklyMenuDAO menuDAO = new WeeklyMenuDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewMenus".equals(action)) {
            List<WeeklyMenu> menus = menuDAO.getAllMenus();
            for (WeeklyMenu menu : menus) {
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
            }
            request.setAttribute("menus", menus);
            request.getRequestDispatcher("/Menu/list.jsp").forward(request, response);
        } else if ("add".equals(action)) {
            request.getRequestDispatcher("/Menu/add.jsp").forward(request, response);
        } else if ("searchMenus".equals(action)) {
            String name = request.getParameter("menuName");
            List<WeeklyMenu> menus = menuDAO.searchMenus(name);
            for (WeeklyMenu menu : menus) {
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
            }
            request.setAttribute("menus", menus);
            request.getRequestDispatcher("/Menu/list.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            WeeklyMenu menu = menuDAO.getMenuById(menuId);
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/Menu/edit.jsp").forward(request, response);
        } else {
            List<WeeklyMenu> menus = menuDAO.getAllMenus();
            for (WeeklyMenu menu : menus) {
                menu.setFormatedDate(formatDateWithDayOfWeek(menu.getDate()));
            }
            request.setAttribute("menus", menus);
            request.getRequestDispatcher("/Menu/list.jsp").forward(request, response);
        }
    }

    private String formatDateWithDayOfWeek(java.sql.Date date) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        date = date == null ? currentDate : date;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.ENGLISH);
        return sdf.format(date);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("addMenu".equals(action)) {
            handleAddMenu(request, response);
        } else if ("updateMenu".equals(action)) {
            handleUpdateMenu(request, response);
        } else if ("deleteMenu".equals(action)) {
            handleDeleteMenu(request, response);
        }
    }

    private void handleAddMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        WeeklyMenu menu = extractMenuFromRequest(request);
        String errorMessage = validateMenu(menu);
        if (errorMessage == null) {
            menuDAO.addMenu(menu);
            response.sendRedirect("weeklyMenu?action=viewMenus");
        } else {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/Menu/add.jsp").forward(request, response);
        }
    }

    private void handleUpdateMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        WeeklyMenu menu = extractMenuFromRequest(request);
        String errorMessage = validateMenu(menu);
        if (errorMessage == null) {
            menuDAO.updateMenu(menu);
            response.sendRedirect("weeklyMenu?action=viewMenus");
        } else {
            request.setAttribute("error", errorMessage);
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/Menu/edit.jsp").forward(request, response);
        }
    }

    private void handleDeleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        menuDAO.deleteMenu(menuId);
        response.sendRedirect("weeklyMenu?action=viewMenus");
    }

    private WeeklyMenu extractMenuFromRequest(HttpServletRequest request) {
        WeeklyMenu menu = new WeeklyMenu();
        menu.setMenuId(request.getParameter("menuId") != null ? Integer.parseInt(request.getParameter("menuId")) : 0);
        menu.setMenuName(request.getParameter("menuName").trim());
        menu.setDescription(request.getParameter("description").trim());
        menu.setDietType(request.getParameter("dietType").trim());
        int price = 0;
        try {
            price = Integer.parseInt(request.getParameter("price"));
        } catch (Exception e) {
            price = -1;
        }
        menu.setPrice(price);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = df.parse(request.getParameter("date").trim());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            menu.setDate(sqlDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

    private String validateMenu(WeeklyMenu menu) {
        try {
            if (menu.getMenuName() == null || menu.getMenuName().isEmpty()) {
                return "Menu name is required.";
            }
            if (menu.getDescription() == null || menu.getDescription().isEmpty()) {
                return "Description is required.";
            }
            if (menu.getDietType() == null || menu.getDietType().isEmpty()) {
                return "Diet type is required.";
            }
            if (menu.getDate() == null) {
                return "Date is required.";
            }

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            if (menu.getDate().before(currentDate)) {
                return "Date must be today or in the future.";
            }
            if (menu.getPrice() < 0) {
                return "Price must be from 0.";
            }
            return null;
        } catch (Exception e) {
            return "Have a error with data";
        }
    }
}
