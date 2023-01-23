package org.example.servlets;

import org.example.dao.OfficeDAO;
import org.example.dao.RoleDAO;
import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.EncryptDecryptUtils;
import org.example.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersDAO dao = new UsersDAO();
    private OfficeDAO officeDAO = new OfficeDAO();

    private RoleDAO roleDAO = new RoleDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> all = dao.getAll();
        System.out.println("Users size: " + all.size());
        req.setAttribute("users", all);

        String action = req.getParameter("action"); // can be C, U, D or No such parametr at all!

        if (action != null){
            switch (action) {
                case "C":
                    req.setAttribute("offices", officeDAO.getAll());
                    ServletUtils.forward(req, resp, "jsp/created.jsp");
                    return;

                case "U":
                    req.setAttribute("offices", officeDAO.getAll());
                    req.setAttribute("user", dao.getById(Integer.parseInt(req.getParameter("userId"))));
                    req.setAttribute("roles", roleDAO.getAll());
                    ServletUtils.forward(req, resp, "jsp/updateUser.jsp");
                    return;

                case "D":
                    req.setAttribute("deleteUser", dao.getById(Integer.parseInt(req.getParameter("userId"))));
                    ServletUtils.forward(req, resp, "jsp/deleteUser.jsp");
                    return;
            }
        }
        ServletUtils.forward(req, resp, "jsp/users.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("C")) {

            User user = new User();

            user.setName(req.getParameter("name").trim());
            user.setEmail(req.getParameter("email").trim().toLowerCase());
            user.setPsw(EncryptDecryptUtils.encrypt(req.getParameter("password")));

            String officeLocation = req.getParameter("officeLocation");
            user.setOffice(officeDAO.getOfficeByLocation(officeLocation));
            user.set_active(Boolean.parseBoolean(req.getParameter("is_active")));

            boolean isAdded = dao.insert(user);
            req.setAttribute("msg", isAdded ? "User was added" : "User not added");

            Set<User> all = dao.getAll();
            System.out.println("Users size: " + all.size());
            req.setAttribute("users", all);

            ServletUtils.forward(req, resp, "jsp/users.jsp");
        }

        if (action.equals("U")) {

            User updateUser = dao.getById(Integer.parseInt(req.getParameter("userId")));
            updateUser.setName(req.getParameter("name").trim());
            updateUser.setEmail(req.getParameter("email").trim().toLowerCase());
            updateUser.setPsw(EncryptDecryptUtils.encrypt(req.getParameter("password")));
            String officeLocation = req.getParameter("officeLocation");
            updateUser.setOffice(officeDAO.getOfficeByLocation(officeLocation));
            updateUser.set_active(req.getParameter("is_active") != null);


            boolean isUpdated = dao.update(updateUser);
            req.setAttribute("msg", isUpdated ? "User was updated" : "User not updated");

            Set<User> all = dao.getAll();
            System.out.println("Users size: " + all.size());
            req.setAttribute("users", all);

            ServletUtils.forward(req, resp, "jsp/users.jsp");

        }

        if (action.equals("D")) {

            dao.delete(Integer.parseInt(req.getParameter("userId")));

            Set<User> all = dao.getAll();
            System.out.println("Users size: " + all.size());
            req.setAttribute("users", all);

            ServletUtils.forward(req, resp, "jsp/users.jsp");
        }

        ServletUtils.forward(req, resp, "jsp/users.jsp");
    }
}