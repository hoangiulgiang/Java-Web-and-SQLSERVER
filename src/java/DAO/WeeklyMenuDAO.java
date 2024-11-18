/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author duong minh hoang
 */
import Model.Recipe;
import Model.WeeklyMenu;
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class WeeklyMenuDAO {

    private Connection connection;

    public WeeklyMenuDAO() {
        try {
            connection = DBContext.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection: " + e);
        }
    }

    public List<WeeklyMenu> getAllMenus() {
        List<WeeklyMenu> menus = new ArrayList<>();
        try (Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WeeklyMenus")) {

            while (resultSet.next()) {
                WeeklyMenu menu = new WeeklyMenu();
                menu.setMenuId(resultSet.getInt("menuId"));
                menu.setMenuName(resultSet.getString("menuName"));
                menu.setDescription(resultSet.getString("description"));
                menu.setDietType(resultSet.getString("dietType"));
                menu.setDate(resultSet.getDate("date"));
                menu.setPrice(resultSet.getInt("price"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public List<WeeklyMenu> getAllMenusCustomer() {
        List<WeeklyMenu> menus = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WeeklyMenus");

            while (resultSet.next()) {
                WeeklyMenu menu = new WeeklyMenu();
                menu.setMenuId(resultSet.getInt("menuId"));
                menu.setMenuName(resultSet.getString("menuName"));
                menu.setDescription(resultSet.getString("description"));
                menu.setDietType(resultSet.getString("dietType"));
                menu.setDate(resultSet.getDate("date"));
                menu.setPrice(resultSet.getInt("price"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public List<WeeklyMenu> getAllMenusByDate(Date date) {
        List<WeeklyMenu> menus = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM WeeklyMenus where date >= ?");
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                WeeklyMenu menu = new WeeklyMenu();
                menu.setMenuId(resultSet.getInt("menuId"));
                menu.setMenuName(resultSet.getString("menuName"));
                menu.setDescription(resultSet.getString("description"));
                menu.setDietType(resultSet.getString("dietType"));
                menu.setDate(resultSet.getDate("date"));
                menu.setPrice(resultSet.getInt("price"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public List<WeeklyMenu> getAllMenusByDateCorrect(Date date) {
        List<WeeklyMenu> menus = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM WeeklyMenus");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                WeeklyMenu menu = new WeeklyMenu();
                menu.setMenuId(resultSet.getInt("menuId"));
                menu.setMenuName(resultSet.getString("menuName"));
                menu.setDescription(resultSet.getString("description"));
                menu.setDietType(resultSet.getString("dietType"));
                menu.setDate(resultSet.getDate("date"));
                menu.setPrice(resultSet.getInt("price"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public List<WeeklyMenu> searchMenus(String name) {
        List<WeeklyMenu> menus = new ArrayList<>();
        String sql = "SELECT * FROM WeeklyMenus WHERE menuName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    WeeklyMenu menu = new WeeklyMenu();
                    menu.setMenuId(resultSet.getInt("menuId"));
                    menu.setMenuName(resultSet.getString("menuName"));
                    menu.setDescription(resultSet.getString("description"));
                    menu.setDate(resultSet.getDate("date"));
                    menu.setPrice(resultSet.getInt("price"));
                    menus.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public void addMenu(WeeklyMenu menu) {
        String sql = "INSERT INTO WeeklyMenus (menuName, description, dietType, date, price) VALUES (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, menu.getMenuName());
            statement.setString(2, menu.getDescription());
            statement.setString(3, menu.getDietType());
            statement.setDate(4, menu.getDate());
            statement.setInt(5, menu.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add memu: " + e);
        }
    }

    public void updateMenu(WeeklyMenu menu) {
        String sql = "UPDATE WeeklyMenus SET menuName = ?, description = ?, dietType = ?, date=?, price=? WHERE menuId = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, menu.getMenuName());
            statement.setString(2, menu.getDescription());
            statement.setString(3, menu.getDietType());
            statement.setDate(4, menu.getDate());
            statement.setInt(5, menu.getPrice());
            statement.setInt(6, menu.getMenuId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM WeeklyMenus WHERE menuId = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menuId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WeeklyMenu getMenuById(int menuId) {
        WeeklyMenu menu = null;
        String sql = "SELECT * FROM WeeklyMenus WHERE menuId = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menuId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    menu = new WeeklyMenu();
                    menu.setMenuId(resultSet.getInt("menuId"));
                    menu.setMenuName(resultSet.getString("menuName"));
                    menu.setDescription(resultSet.getString("description"));
                    menu.setDietType(resultSet.getString("dietType"));
                    menu.setDate(resultSet.getDate("date"));
                    menu.setPrice(resultSet.getInt("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    public WeeklyMenu getMenuByIdCustomer(int menuId) {
        WeeklyMenu menu = null;
        String sql = "SELECT * FROM WeeklyMenus WHERE menuId = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menuId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    menu = new WeeklyMenu();
                    menu.setMenuId(resultSet.getInt("menuId"));
                    menu.setMenuName(resultSet.getString("menuName"));
                    menu.setDescription(resultSet.getString("description"));
                    menu.setDietType(resultSet.getString("dietType"));
                    menu.setDate(resultSet.getDate("date"));
                    menu.setPrice(resultSet.getInt("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    public List<Recipe> getRecipesForMenu(int menuId) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT r.* FROM Recipes r JOIN  WeeklyMenus as mr ON r.menuId = mr.menuId WHERE mr.menuId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, menuId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setRecipeId(rs.getInt("recipeId"));
                    recipe.setRecipeName(rs.getString("recipeName"));
                    recipe.setIngredients(rs.getString("ingredients"));
                    recipe.setInstructions(rs.getString("instructions"));
                    recipe.setMenuId(menuId);
                    recipes.add(recipe);
                }
            }
        } catch (Exception e) {
            System.out.println("getRecipesForMenu: " + e);
        }
        return recipes;
    }
}
