package org.example.servlets;

import org.example.dao.OfficeDAO;
import org.example.model.Office;
import org.example.model.User;
import org.example.util.ServletUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/office")
public class OfficeServlet extends HttpServlet {

    private OfficeDAO dao = new OfficeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set <Office> office = dao.getAll();
        req.setAttribute("office", office);

        RequestDispatcher rd = req.getRequestDispatcher("jsp/office.jsp");
        rd.forward(req, resp);
    }
}