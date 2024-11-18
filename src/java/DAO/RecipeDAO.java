/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Recipe;
import java.sql.*;
import java.util.*;

/**
 *
 *@author duong minh hoang
 */
public class RecipeDAO {

    private Connection conn;

    public RecipeDAO() {
        try {
            conn = DBContext.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection: " + e);
        }
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipeId, menuId, recipeName, ingredients, instructions FROM Recipes";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(rs.getInt("recipeId"));
                recipe.setMenuId(rs.getInt("menuId"));
                recipe.setRecipeName(rs.getString("recipeName"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setInstructions(rs.getString("instructions"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
    
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;
        String sql = "SELECT recipeId, menuId, recipeName, ingredients, instructions FROM Recipes WHERE recipeId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recipe = new Recipe();
                recipe.setRecipeId(rs.getInt("recipeId"));
                recipe.setMenuId(rs.getInt("menuId"));
                recipe.setRecipeName(rs.getString("recipeName"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setInstructions(rs.getString("instructions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public void addRecipe(Recipe recipe) {
        String sql = "INSERT INTO Recipes (menuId, recipeName, ingredients, instructions) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipe.getMenuId());
            ps.setString(2, recipe.getRecipeName());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getInstructions());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecipe(Recipe recipe) {
        String sql = "UPDATE Recipes SET menuId = ?, recipeName = ?, ingredients = ?, instructions = ? WHERE recipeId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipe.getMenuId());
            ps.setString(2, recipe.getRecipeName());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getInstructions());
            ps.setInt(5, recipe.getRecipeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipe(int recipeId) {
        String sql = "DELETE FROM Recipes WHERE recipeId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
