/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author duong minh hoang
 */
public class OrderItem {

    private int orderItemId;
    private int orderId;
    private int mealId;
    private int quantity;
    private double price;
    private WeeklyMenu menu;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, int orderId, int mealId, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.mealId = mealId;
        this.quantity = quantity;
        this.price = price;
    }

    public WeeklyMenu getMenu() {
        return menu;
    }

    public void setMenu(WeeklyMenu menu) {
        this.menu = menu;
    }
    
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
