/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author duong minh hoang
 */
import Model.Order;
import Model.OrderItem;
import java.sql.*;
import java.util.*;

public class OrderDAO {

    private Connection connection;

    public OrderDAO() {
        try {
            connection = DBContext.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection: " + e);
        }
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO Orders (userId, orderDate, totalAmount, deliveryAddress, phoneNumber, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getAddress());
            stmt.setString(5, order.getPhoneNumber());
            stmt.setString(6, order.getStatus());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                for (OrderItem item : order.getOrderItems()) {
                    addOrderItem(orderId, item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderItem(int orderId, OrderItem item) {
        String query = "INSERT INTO OrderItem (orderId, menuId, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, item.getMealId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM Orders WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setUserId(rs.getInt("userId"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setAddress(rs.getString("deliveryAddress"));
                    order.setPhoneNumber(rs.getString("phoneNumber"));
                    order.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order getOrderByIdAnUser(int orderId, int userId) {
        Order order = null;
        String query = "SELECT * FROM Orders WHERE orderId = ? and userId=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setUserId(rs.getInt("userId"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setAddress(rs.getString("deliveryAddress"));
                    order.setPhoneNumber(rs.getString("phoneNumber"));
                    order.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders order by orderId desc";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setAddress(rs.getString("deliveryAddress"));
                order.setPhoneNumber(rs.getString("phoneNumber"));
                order.setStatus(rs.getString("status"));
                order.setOrderItems(getOrderItemsByOrderId(order.getOrderId()));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> searchOrders(String query) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE orderDate LIKE ? OR phoneNumber LIKE ? OR deliveryAddress LIKE ? order by orderId desc";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String searchQuery = "%" + query + "%";
            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);
            stmt.setString(3, searchQuery);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setUserId(rs.getInt("userId"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setAddress(rs.getString("deliveryAddress"));
                    order.setPhoneNumber(rs.getString("phoneNumber"));
                    order.setStatus(rs.getString("status"));

                    order.setOrderItems(getOrderItemsByOrderId(order.getOrderId()));

                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public void updateOrderStatus(int orderId, String status) {
        String query = "UPDATE Orders SET status = ? WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM OrderItem WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setOrderId(rs.getInt("orderId"));
                    item.setMealId(rs.getInt("menuId"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    orderItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orderHistory = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE userId = ? order by orderId desc";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setUserId(rs.getInt("userId"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setAddress(rs.getString("deliveryAddress"));
                    order.setPhoneNumber(rs.getString("phoneNumber"));
                    order.setStatus(rs.getString("status"));
                    order.setOrderItems(getOrderItemsByOrderId(order.getOrderId()));

                    orderHistory.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

}
