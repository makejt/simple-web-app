package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        String password = req.getParameter("psw");

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);

        if (email.equalsIgnoreCase(user.getEmail()) && password.equals(user.getPsw())) {
            resp.getWriter().println("Hello, welcome back");

        } else {
            resp.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
            resp.getWriter().println("Bad credentials");
            dispatcher.include(req, resp);
        }
    }
}
