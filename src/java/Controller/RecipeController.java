/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.RecipeDAO;
import DAO.WeeklyMenuDAO;
import Model.Recipe;
import Model.WeeklyMenu;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "RecipeController", urlPatterns = {"/recipes"})
public class RecipeController extends HttpServlet {
 private RecipeDAO recipeDAO;
    private WeeklyMenuDAO menuDAO;

    public void init() {
        recipeDAO = new RecipeDAO();
        menuDAO = new WeeklyMenuDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listRecipes(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteRecipe(request, response);
                break;
            default:
                listRecipes(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addRecipe(request, response);
                break;
            case "edit":
                updateRecipe(request, response);
                break;
            default:
                doGet(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<WeeklyMenu> menus = menuDAO.getAllMenus();
        request.setAttribute("menus", menus);
        request.getRequestDispatcher("/Recipes/add.jsp").forward(request, response);
    }

   
    private void listRecipes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        List<WeeklyMenu> menus = menuDAO.getAllMenus();
        Map<Integer, String> menuMap = new HashMap<>();
        for (WeeklyMenu menu : menus) {
            menuMap.put(menu.getMenuId(), menu.getMenuName());
        }
        request.setAttribute("recipes", recipes);
        request.setAttribute("menuMap", menuMap);
        request.getRequestDispatcher("/Recipes/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        Recipe existingRecipe = recipeDAO.getRecipeById(recipeId);
        List<WeeklyMenu> menus = menuDAO.getAllMenus();
        request.setAttribute("menus", menus);
        request.setAttribute("recipe", existingRecipe);
        request.getRequestDispatcher("/Recipes/edit.jsp").forward(request, response);
    }

    private void deleteRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        recipeDAO.deleteRecipe(recipeId);
        response.sendRedirect(request.getContextPath() + "/recipes");
    }

    private void addRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId").trim());
        String recipeName = request.getParameter("recipeName").trim();
        String ingredients = request.getParameter("ingredients").trim();
        String instructions = request.getParameter("instructions").trim();

        if (recipeName == null || recipeName.isEmpty() || ingredients == null || ingredients.isEmpty() || instructions == null || instructions.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields.");
            request.setAttribute("menuId", menuId);
            request.setAttribute("recipeName", recipeName);
            request.setAttribute("ingredients", ingredients);
            request.setAttribute("instructions", instructions);
            showAddForm(request, response); 
            return;
        }

        Recipe newRecipe = new Recipe(0, menuId, recipeName, ingredients, instructions); // recipeId is auto-generated, so set it to 0
        recipeDAO.addRecipe(newRecipe);

        response.sendRedirect(request.getContextPath() + "/recipes");
    }

    private void updateRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId").trim());
        int menuId = Integer.parseInt(request.getParameter("menuId").trim());
        String recipeName = request.getParameter("recipeName").trim();
        String ingredients = request.getParameter("ingredients").trim();
        String instructions = request.getParameter("instructions").trim();

        if (recipeName == null || recipeName.isEmpty() || ingredients == null || ingredients.isEmpty() || instructions == null || instructions.isEmpty()) {
            request.setAttribute("error", "Please fill in all fields.");
            Recipe existingRecipe = new Recipe(recipeId, menuId, recipeName, ingredients, instructions);
            List<WeeklyMenu> menus = menuDAO.getAllMenus();
            request.setAttribute("menus", menus);
            request.setAttribute("recipe", existingRecipe);
            showEditForm(request, response); 
            return;
        }

        Recipe updatedRecipe = new Recipe(recipeId, menuId, recipeName, ingredients, instructions);
        recipeDAO.updateRecipe(updatedRecipe);

        response.sendRedirect(request.getContextPath() + "/recipes");
    }
}
