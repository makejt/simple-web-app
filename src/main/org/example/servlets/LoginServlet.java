package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.EncryptDecryptUtils;
import org.example.util.ServletUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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
        String password = EncryptDecryptUtils.encrypt(req.getParameter("psw"));

        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email.trim());

        if (user == null) {
            resp.getWriter().println("User does not exist! Please <a href = 'registration'> REGISTER </a>");
            dispatcher.include(req, resp);
        } else if (password.equals(user.getPsw())) {

            HttpSession session = req.getSession();

            int timeout = Integer.parseInt(getServletConfig().getInitParameter("timeout"));
            session.setMaxInactiveInterval(timeout);

            ServletUtils.saveSessionUser(req, user);

            dispatcher = req.getRequestDispatcher("welcome");
            dispatcher.forward(req, resp);
            // !!!! Добавить, если пользователь не активен, вот ссылка, пройди активацию

        } else {
            dispatcher = req.getRequestDispatcher("login.html");
            resp.getWriter().println("Bad credentials");
            dispatcher.include(req, resp);
        }
    }
}