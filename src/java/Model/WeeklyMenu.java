/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
public class WeeklyMenu {
    private int menuId;
    private String menuName;
    private String description;
    private String dietType;
    private Date date;
    private String formatedDate;
    private List<Recipe> recipe;
    private int price;

    public WeeklyMenu() {
    }

    public WeeklyMenu(int menuId, String menuName, String description, String dietType) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.description = description;
        this.dietType = dietType;
    }

    public List<Recipe> getRecipe() {
        return recipe;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setRecipe(List<Recipe> recipe) {
        this.recipe = recipe;
    }
    
    public Date getDate() {
        return date;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }
}
