package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.*;

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

@WebServlet("/forgotPsw")
public class ForgotPswServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("forgotPsw.html");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email").trim();

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);

        if(user == null){
            resp.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("forgotPsw.html");
            resp.getWriter().println("<b> There is no user with such an email</b>");
            dispatcher.include(req, resp);
        }

        if(user != null){

            // генерируем пароль

            String tempPsw = PswGenerator.generatePsw();

            // шифруем пароль и обновляем пароль в БД

            String encryptTempPsw = EncryptDecryptUtils.encrypt(tempPsw);
            String UPDATE_PSW = "UPDATE user SET password = '" + encryptTempPsw + "' WHERE email = '" + email + "'";

                try(Connection connection = DBUtils.getConnection()) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(UPDATE_PSW); }
             catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
                // направляем пользователю временный пароль и просим его сменить
            String content = "Your new password = " + tempPsw + ". Please check password";
            MailUtils.send(email, "temporary password", content);

            resp.getWriter().println("<b> Your temporary password was sending by email. Check your email-box</b>");
        }
    }
}