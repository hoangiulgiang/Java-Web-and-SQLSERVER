/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MealPlanDetails;
import java.sql.*;
import java.util.*;

/**
 *
 * @author duong minh hoang
 */
public class MealPlanDetailsDAO {

    private Connection connection;

    public MealPlanDetailsDAO() {
        try {
            connection = DBContext.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection: " + e);
        }
    }

    public List<MealPlanDetails> getDetailsByPlanId(int planId) throws SQLException {
        String query = "SELECT * FROM MealPlanDetails WHERE planId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, planId);
        ResultSet rs = pstmt.executeQuery();
        List<MealPlanDetails> details = new ArrayList<>();
        while (rs.next()) {
            MealPlanDetails detail = new MealPlanDetails();
            detail.setDetailId(rs.getInt("detailId"));
            detail.setPlanId(rs.getInt("planId"));
            detail.setDayOfWeek(rs.getString("dayOfWeek"));
            detail.setMealId(rs.getInt("menuId"));
            details.add(detail);
        }
        return details;
    }

    public MealPlanDetails getDetailById(int detailId) throws SQLException {
        String query = "SELECT * FROM MealPlanDetails WHERE detailId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, detailId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            MealPlanDetails detail = new MealPlanDetails();
            detail.setDetailId(rs.getInt("detailId"));
            detail.setPlanId(rs.getInt("planId"));
            detail.setDayOfWeek(rs.getString("dayOfWeek"));
            detail.setMealId(rs.getInt("menuId"));
            return detail;
        }
        return null;
    }

    public void addDetail(MealPlanDetails detail) throws SQLException {
        String query = "INSERT INTO MealPlanDetails (planId, dayOfWeek, menuId) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, detail.getPlanId());
        pstmt.setString(2, detail.getDayOfWeek());
        pstmt.setInt(3, detail.getMealId());
        pstmt.executeUpdate();
    }

    public void updateDetail(MealPlanDetails detail) throws SQLException {
        String query = "UPDATE MealPlanDetails SET dayOfWeek = ?, menuId = ? WHERE detailId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, detail.getDayOfWeek());
        pstmt.setInt(2, detail.getMealId());
        pstmt.setInt(3, detail.getDetailId());
        pstmt.executeUpdate();
    }

    public void deleteDetail(int detailId) throws SQLException {
        String query = "DELETE FROM MealPlanDetails WHERE detailId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, detailId);
        pstmt.executeUpdate();
    }

    public boolean isDayAlreadyAdded(int planId, String dayOfWeek) {
        String query = "SELECT 1 FROM MealPlanDetails WHERE planId = ? AND dayOfWeek = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, planId);
            stmt.setString(2, dayOfWeek);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
