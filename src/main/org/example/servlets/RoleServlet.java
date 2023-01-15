package org.example.servlets;

import org.example.dao.RoleDAO;
import org.example.model.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    private RoleDAO dao = new RoleDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Set<Role> roles = dao.getAll();
            req.setAttribute("role", roles);

            RequestDispatcher rd = req.getRequestDispatcher("jsp/roles.jsp");
            rd.forward(req, resp);
        }
    }