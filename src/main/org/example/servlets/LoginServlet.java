package org.example.servlets;

import org.example.util.AppConstants;

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
        String email = req.getParameter("email");
        String password = req.getParameter("psw");

        if(email.trim().equalsIgnoreCase(AppConstants.USER_EMAIL) && password.equals(AppConstants.USER_PSW)) {
            resp.getWriter().println("Hello, welcome back");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            resp.getWriter().println("Bad credentials");
            rd.include(req, resp);
        }




    }
}
