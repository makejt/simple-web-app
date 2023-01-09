package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.DBUtils;
import org.example.util.EncryptDecryptUtils;
import org.example.util.MailUtils;

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

@WebServlet("/changePsw")
public class ChangePswServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("changePsw.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email").trim();

        String password = EncryptDecryptUtils.encrypt(req.getParameter("psw"));
        String password1 = EncryptDecryptUtils.encrypt(req.getParameter("psw1"));
        String password2 = EncryptDecryptUtils.encrypt(req.getParameter("psw2"));

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);

        RequestDispatcher dispatcher = req.getRequestDispatcher("changePsw.html");
        resp.setContentType("text/html;charset=UTF-8");

        if(user == null){
            resp.getWriter().println("<b> There is no user with such an email</b>");
            dispatcher.include(req, resp);
        }

        if (!password1.equals(password2)) {
            resp.getWriter().println("Passwords are not equals");
            dispatcher.include(req, resp);
        }


        if (email.equalsIgnoreCase(user.getEmail()) &&
                password.equals(user.getPsw()) &&
                password1.equals(password2)) {


            String UPDATE_PSW = "UPDATE user SET password = '" + password1 + "' WHERE email = '" + email + "'";

            try(Connection connection = DBUtils.getConnection(DB_URL)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(UPDATE_PSW);
                resp.getWriter().println("<b> Your password was updated. Please</b> <a href='login'>login<a>");
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            resp.getWriter().println("Bad credentials");
            dispatcher.include(req, resp);
        }
    }
}