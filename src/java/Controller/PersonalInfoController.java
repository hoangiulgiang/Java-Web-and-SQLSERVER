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
import javax.servlet.http.HttpSession;

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "PersonalInfoController", urlPatterns = {"/personalInfo"})
public class PersonalInfoController extends HttpServlet {

    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User uLogin = (User) session.getAttribute("user");
        if (uLogin == null || (uLogin != null && !uLogin.getRole().equals("user"))) {
            response.sendRedirect("login?error=You can not access to resourse");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "editProfile":
                editProfilePage(request, response);
                break;
            case "editPassword":
                editPasswordPage(request, response);
                break;
            default:
                viewProfile(request, response);
                break;
        }
    }

    private void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            User user = userDao.getUserById(userId);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/Customer/personal.jsp").forward(request, response);
            } else {
                response.sendRedirect("404");
            }
        } catch (Exception e) {
            response.sendRedirect("404");
        }
    }

    private void editProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");

        User user = userDao.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/Customer/editProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("404");
        }
    }

    private void editPasswordPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");

        User user = userDao.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/Customer/changePassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("404");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "changePassword":
                updatePassword(request, response);
                break;
            case "updatePersonalInfo":
                updateProfile(request, response);
                break;
            default:
                response.sendRedirect("personalInfo?error=Action is not valid");
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userDao.getUserById(userId);
            if (user != null) {
                String password = request.getParameter("currentPassword");
                String newPassword = request.getParameter("newPassword");
                String passConfirm = request.getParameter("confirmPassword");
                if (!user.getRole().equals("user")) {
                    request.setAttribute("user", user);
                    request.setAttribute("error", "You can not change password here");
                    request.getRequestDispatcher("/Customer/changePassword.jsp").forward(request, response);
                    return;
                }

                if (!user.getPassword().equals(password)) {
                    request.setAttribute("user", user);
                    request.setAttribute("error", "Your old password is not correct");
                    request.getRequestDispatcher("/Customer/changePassword.jsp").forward(request, response);
                    return;
                }

                if (!newPassword.endsWith(passConfirm)) {
                    request.setAttribute("user", user);
                    request.setAttribute("error", "Your confirm password is not correct");
                    request.getRequestDispatcher("/Customer/changePassword.jsp").forward(request, response);
                    return;
                }

                String passwordPattern = "^.{8,}$";

                if (!newPassword.matches(passwordPattern)) {
                    request.setAttribute("user", user);
                    request.setAttribute("error", "Password must be form 8 character");
                    request.getRequestDispatcher("/Customer/changePassword.jsp").forward(request, response);
                    return;
                }
                userDao.updateUserPassword(newPassword, userId);
                response.sendRedirect("personalInfo?success=Update password successfully");
            } else {
                response.sendRedirect("personalInfo?error=Please login again");
            }
        } catch (Exception e) {
            response.sendRedirect("personalInfo?error=Have a error with data");
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            User user = userDao.getUserById(userId);
            if (user == null) {
                response.sendRedirect("login?error=Please login before change");
                return;
            }

            if (!isValidEmail(email)) {
                request.setAttribute("user", user);
                request.setAttribute("error", "Invalid email format");
                request.getRequestDispatcher("/Customer/editProfile.jsp").forward(request, response);
                return;
            }

            if (!isValidPhoneNumber(phone)) {
                request.setAttribute("user", user);
                request.setAttribute("error", "Invalid phone number format");
                request.getRequestDispatcher("/Customer/editProfile.jsp").forward(request, response);
                return;
            }

            if (userDao.emailExistsForOtherUsers(email, userId)) {
                request.setAttribute("user", user);
                request.setAttribute("error", "Email already exists for another user");
                request.getRequestDispatcher("/Customer/editProfile.jsp").forward(request, response);
                return;
            }

            if (userDao.phoneExistsForOtherUsers(phone, userId)) {
                request.setAttribute("user", user);
                request.setAttribute("error", "Phone number already exists for another user");
                request.getRequestDispatcher("/Customer/editProfile.jsp").forward(request, response);
                return;
            }

            User userUpdate = new User(userId, userName, email, null, phone);
            userDao.updatePersonal(userUpdate);
            response.sendRedirect("personalInfo?success=Update profile successfully");
        } catch (Exception e) {
            response.sendRedirect("personalInfo?error=Have a error with data");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^0\\d{9,11}$";
        return phoneNumber.matches(regex);
    }

}
