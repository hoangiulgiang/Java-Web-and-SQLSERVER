/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.WeeklyMenuDAO;
import Model.Order;
import Model.OrderItem;
import Model.User;
import Model.WeeklyMenu;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;
    private WeeklyMenuDAO mealDAO;

    public void init() {
        orderDAO = new OrderDAO();
        mealDAO = new WeeklyMenuDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User uLogin = (User) session.getAttribute("user");
        if (uLogin == null || (uLogin != null && !uLogin.getRole().equals("user"))) {
            response.sendRedirect("login?error=You can not access to resourse");
            return;
        }
        String menuId = request.getParameter("menuId");
        try {
            WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
            WeeklyMenu weeklyMenu = weeklyMenuDao.getMenuById(Integer.parseInt(menuId));
            if (weeklyMenu != null) {
                List<WeeklyMenu> mealList = new ArrayList<>();
                mealList.add(weeklyMenu);
                request.setAttribute("mealList", mealList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/order.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("menu?error=Menu is not exist");
            }
        } catch (Exception e) {
            response.sendRedirect("menu?error=Menu is not exist");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User uLogin = (User) session.getAttribute("user");
        if (uLogin == null || (uLogin != null && !uLogin.getRole().equals("user"))) {
            response.sendRedirect("login?error=You can not access to resourse");
            return;
        }
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String[] menus = request.getParameterValues("menus");
            List<OrderItem> orderItems = new ArrayList<>();
            double totalAmount = 0;
            WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
            for (String menuId : menus) {
                int menu = Integer.parseInt(menuId);
                WeeklyMenu meal = weeklyMenuDao.getMenuById(menu);
                int quantity = Integer.parseInt(request.getParameter("quantity_" + meal.getMenuId()));
                if (quantity > 0) {
                    OrderItem item = new OrderItem();
                    item.setMealId(meal.getMenuId());
                    item.setQuantity(quantity);
                    item.setPrice(meal.getPrice() * quantity);
                    totalAmount += item.getPrice();
                    orderItems.add(item);
                }
            }

            Order order = new Order();
            order.setUserId(userId);
            order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
            order.setTotalAmount(totalAmount);
            order.setAddress(address);
            order.setPhoneNumber(phoneNumber);
            order.setStatus("Pending");
            order.setOrderItems(orderItems);

            orderDAO.addOrder(order);
            response.sendRedirect("orderHistory");
        } catch (Exception e) {
            response.sendRedirect("menu?error=Have a error");
        }
    }
}
