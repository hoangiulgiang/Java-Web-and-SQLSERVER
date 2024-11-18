/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author duong minh hoang
 */
public class PersonalMealPlan {

    private int planId;
    private int userId;
    private String planName;
    private Date startDate;
    private int weeks;

    public PersonalMealPlan() {
    }

    public PersonalMealPlan(int planId, int userId, String planName, Date startDate, int weeks) {
        this.planId = planId;
        this.userId = userId;
        this.planName = planName;
        this.startDate = startDate;
        this.weeks = weeks;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    
}
