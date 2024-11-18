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

/**
 *
 * @author duong minh hoang
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.registerUser(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("confirmPassword");

        if (!isValidEmail(email)) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            request.setAttribute("error", "Invalid phone number format");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        if (userDao.userNameExist(userName)) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        if (userDao.emailExists(email)) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }
        if (userDao.phoneExists(phone)) {
            request.setAttribute("error", "Phone number already exists");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(passwordConfirm)) {
            request.setAttribute("error", "Password confirm is not correct");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        String userNamePattern = "^[a-zA-Z0-9]{5,}$";
        if (!userName.matches(userNamePattern)) {
            request.setAttribute("error", "Username must be form 5 character");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }
        
        String passwordPattern = "^.{8,}$";

        if (!password.matches(passwordPattern)) {
            request.setAttribute("error", "Password must be form 8 character");
            request.getRequestDispatcher("/Customer/register.jsp").forward(request, response);
            return;
        }

        User user = new User(0, userName, email, password, phone);
        user.setStatus(1);
        userDao.registerUser(user);
        response.sendRedirect("login?success=Register successfully");
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^0\\d{9,11}$";
        return phoneNumber.matches(regex);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
