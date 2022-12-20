package org.example.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/number")
public class NumberServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("number.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        int minNum = Integer.parseInt(req.getParameter("MinNumber"));
        int maxNum = Integer.parseInt(req.getParameter("MaxNumber"));

        if(minNum > maxNum) {

            RequestDispatcher rd= req.getRequestDispatcher("number.html");
            resp.getWriter().println("Min number is larger then max number. Try again!!!");
            rd.include(req, resp);

        } else {
            Random random = new Random();
            int value = random.nextInt(maxNum - minNum + 1) + minNum;
            RequestDispatcher rd = req.getRequestDispatcher("number.html");
            resp.getWriter().println("Your number is " + value + ". Try again!");
            rd.include(req, resp);
        }
    }
}
