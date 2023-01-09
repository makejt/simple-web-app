package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.DBUtils;
import org.example.util.EncryptDecryptUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.util.AppConstants.DB_URL;

@WebServlet("/activate")

public class ActivationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
        resp.getWriter().println("Please enter email and password to activate your profile");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();

        String password = EncryptDecryptUtils.encrypt(req.getParameter("psw"));

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);

        if (email.equalsIgnoreCase(user.getEmail()) && password.equals(user.getPsw())) {
            resp.getWriter().println("Thank you for registration!");

            String UPDATE_ACTIVE = "UPDATE user SET is_active = 'A' WHERE email = '" + email + "'";

            try(Connection connection = DBUtils.getConnection(DB_URL)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(UPDATE_ACTIVE); }
            catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
            resp.getWriter().println("Bad credentials");
            dispatcher.include(req, resp);
        }
    }
}