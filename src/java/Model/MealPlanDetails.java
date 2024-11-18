/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author duong minh hoang
 */
public class MealPlanDetails {

    private int detailId;
    private int planId;
    private String dayOfWeek;
    private int mealId;
    private WeeklyMenu menu;

    public MealPlanDetails() {
    }

    public MealPlanDetails(int detailId, int planId, String dayOfWeek, int mealId) {
        this.detailId = detailId;
        this.planId = planId;
        this.dayOfWeek = dayOfWeek;
        this.mealId = mealId;
    }

    public WeeklyMenu getMenu() {
        return menu;
    }

    public void setMenu(WeeklyMenu menu) {
        this.menu = menu;
    }
    
    public int getDetailId() {
        return detailId;
    }
    

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
}
