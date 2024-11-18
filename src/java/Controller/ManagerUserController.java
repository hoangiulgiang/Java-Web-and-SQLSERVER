/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "ManagerUserController", urlPatterns = {"/manager-user"})
public class ManagerUserController extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "view":
                this.viewUser(request, response);
                break;
            case "edit":
                this.showEditForm(request, response);
                break;
            case "delete":
                this.deleteUser(request, response);
                break;
            case "new":
                this.showNewForm(request, response);
                break;
            default:
                this.listUsers(request, response);
                break;
        }
    }

    private void viewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User existingUser = userDAO.getUserById(userId);
            if (existingUser != null) {
                request.setAttribute("user", existingUser);
                request.getRequestDispatcher("/Staff/viewUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("manager-user?error=User not found");
            }
        } catch (Exception e) {
            response.sendRedirect("manager-user?error=Hava a error");
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = userDAO.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/Staff/managerUser.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Staff/userForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User existingUser = userDAO.getUserById(userId);
            if (existingUser != null) {
                request.setAttribute("user", existingUser);
                request.getRequestDispatcher("/Staff/userForm.jsp").forward(request, response);
            } else {
                response.sendRedirect("manager-user?error=Can not found this user");
            }
        } catch (Exception e) {
            response.sendRedirect("manager-user?error=Hava a error");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userDAO.deleteUser(userId);
        response.sendRedirect("manager-user?success=User deleted successfully");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "insert":
                this.insertUser(request, response);
                break;
            case "update":
                this.updateUser(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        int status = Integer.parseInt(request.getParameter("status"));

        if (isValidInput(userName, email, password, phone)) {
            User newUser = new User(0, userName, email, password, phone, role, status);
            userDAO.insertUser(newUser);
            response.sendRedirect("manager-user?success=User added successfully");
        } else {
            request.setAttribute("errorMessage", "Invalid input data. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/Staff/userForm.jsp").forward(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        int status = Integer.parseInt(request.getParameter("status"));
        if (password.trim().length() == 0) {
            password = request.getParameter("oldPassword");
        }
        if (isValidInput(userName, email, password, phone)) {
            User user = new User(userId, userName, email, password, phone, role, status);
            userDAO.updateUser(user);
            response.sendRedirect("manager-user?success=User updated successfully");
        } else {
            request.setAttribute("errorMessage", "Invalid input data. Please ensure all fields are correctly filled.");
            User existingUser = new User(userId, userName, email, password, phone, role, status);
            request.setAttribute("user", existingUser);
            request.getRequestDispatcher("/Staff/userForm.jsp").forward(request, response);
        }
    }

    private boolean isValidInput(String userName, String email, String password, String phone) {
        String userNamePattern = "^[a-zA-Z0-9]{5,}$";
        String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        String passwordPattern = "^.{8,}$";
        String phonePattern = "^\\d{10,12}$";

        return userName.matches(userNamePattern)
                && email.matches(emailPattern)
                && password.matches(passwordPattern)
                && phone.matches(phonePattern);
    }

}
