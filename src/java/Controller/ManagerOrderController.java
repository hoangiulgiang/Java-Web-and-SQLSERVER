/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.WeeklyMenuDAO;
import Model.Order;
import Model.OrderItem;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "ManagerOrderController", urlPatterns = {"/manager-order"})
public class ManagerOrderController extends HttpServlet {

    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAO();
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
        switch (action) {
            case "view":
                this.viewOrder(request, response);
                break;
            case "search":
                this.SearchOrder(request, response);
                break;
            case "update":
                this.changeStatusOrder(request, response);
                break;
            default:
                this.managerOrder(request, response);
                break;
        }
    }

    private void viewOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String orderId = request.getParameter("orderId");
            Order order = orderDAO.getOrderById(Integer.parseInt(orderId));
            if (order != null) {
                List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(Integer.parseInt(orderId));
                WeeklyMenuDAO weeklyMenuDao = new WeeklyMenuDAO();
                for (OrderItem orderItem : orderItems) {
                    orderItem.setMenu(weeklyMenuDao.getMenuById(orderItem.getMealId()));
                }
                request.setAttribute("orderItems", orderItems);
                request.setAttribute("order", order);
                request.getRequestDispatcher("/Staff/viewDetailOrder.jsp").forward(request, response);
            } else {
                response.sendRedirect("manager-order?error=Can not find order");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void managerOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Order> orderList = orderDAO.getAllOrders();
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/Staff/staffOrderManagement.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void SearchOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String query = request.getParameter("query");
            List<Order> orderList = orderDAO.searchOrders(query);
            request.setAttribute("query", query);
            request.setAttribute("orderList", orderList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Staff/staffOrderManagement.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {

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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "changeStatus":
                this.changeStatusOrder(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void changeStatusOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = orderDAO.getOrderById(orderId);
            if (order != null) {
                String status = request.getParameter("status");
                orderDAO.updateOrderStatus(orderId, status);
                response.sendRedirect("manager-order?success=Update status order success");
            } else {
                response.sendRedirect("manager-order?error=Can not found this order");
                return;
            }
        } catch (Exception e) {
            response.sendRedirect("manager-order?error=Can not found this order");
        }
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
