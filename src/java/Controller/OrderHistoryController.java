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
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "OrderHistoryController", urlPatterns = {"/orderHistory"})
public class OrderHistoryController extends HttpServlet {

    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User uLogin = (User) session.getAttribute("user");
        if (uLogin == null || (uLogin != null && !uLogin.getRole().equals("user"))) {
            response.sendRedirect("login?error=You can not access to resourse");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "view":
                this.detailOrder(request, response);
                break;
            default:
                this.listOrder(request, response);
        }

    }

    private void detailOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = orderDAO.getOrderByIdAnUser(orderId, userId);
            if (order != null) {
                List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(orderId);
                WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
                for (OrderItem orderItem : orderItems) {
                    orderItem.setMenu(weeklyMenuDao.getMenuById(orderItem.getMealId()));
                }
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/orderDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("historyOrder?error=Can not found this order");
            }

        } catch (Exception e) {
            response.sendRedirect("historyOrder?error=Have a error");
        }
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            List<Order> orderHistory = orderDAO.getOrdersByUserId(userId);
            request.setAttribute("orderHistory", orderHistory);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/historyOrder.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("personalInfo?error=Have a error");
        }
    }

}
