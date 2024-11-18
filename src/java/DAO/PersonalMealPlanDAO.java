/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author duong minh hoang
 */
import Model.PersonalMealPlan;
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class PersonalMealPlanDAO {

    private Connection connection;

    public PersonalMealPlanDAO() {
        try {
            connection = DBContext.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection: " + e);
        }
    }

    public List<PersonalMealPlan> getPlansByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM PersonalMealPlan WHERE userId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        List<PersonalMealPlan> plans = new ArrayList<>();
        while (rs.next()) {
            PersonalMealPlan plan = new PersonalMealPlan();
            plan.setPlanId(rs.getInt("planId"));
            plan.setUserId(rs.getInt("userId"));
            plan.setPlanName(rs.getString("planName"));
            plan.setStartDate(rs.getDate("startDate"));
            plan.setWeeks(rs.getInt("weeks"));
            plans.add(plan);
        }
        return plans;
    }

    public PersonalMealPlan getPlanById(int planId) {
        try {
            String query = "SELECT * FROM PersonalMealPlan WHERE planId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, planId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PersonalMealPlan plan = new PersonalMealPlan();
                plan.setPlanId(rs.getInt("planId"));
                plan.setUserId(rs.getInt("userId"));
                plan.setPlanName(rs.getString("planName"));
                plan.setStartDate(rs.getDate("startDate"));
                plan.setWeeks(rs.getInt("weeks"));
                return plan;
            }
        }catch(Exception e) {
            
        }
        return null;
    }
    
    public PersonalMealPlan planExists(Date date, int userId) {
        try {
            String query = "SELECT * FROM PersonalMealPlan WHERE startDate = ? and userId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, date);
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PersonalMealPlan plan = new PersonalMealPlan();
                plan.setPlanId(rs.getInt("planId"));
                plan.setUserId(rs.getInt("userId"));
                plan.setPlanName(rs.getString("planName"));
                plan.setStartDate(rs.getDate("startDate"));
                plan.setWeeks(rs.getInt("weeks"));
                return plan;
            }
        }catch(Exception e) {
            
        }
        return null;
        
    }

    public void addPlan(PersonalMealPlan plan) throws SQLException {
        String query = "INSERT INTO PersonalMealPlan (userId, planName, startDate, weeks) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, plan.getUserId());
        pstmt.setString(2, plan.getPlanName());
        pstmt.setDate(3, plan.getStartDate());
        pstmt.setInt(4, plan.getWeeks());
        pstmt.executeUpdate();
    }

    public void updatePlan(PersonalMealPlan plan) throws SQLException {
        String query = "UPDATE PersonalMealPlan SET planName = ?, startDate = ?, weeks = ? WHERE planId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, plan.getPlanName());
        pstmt.setDate(2, plan.getStartDate());
        pstmt.setInt(3, plan.getWeeks());
        pstmt.setInt(4, plan.getPlanId());
        pstmt.executeUpdate();
    }

    public void deletePlan(int planId) throws SQLException {
        String query = "DELETE FROM PersonalMealPlan WHERE planId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, planId);
        pstmt.executeUpdate();
    }
}
