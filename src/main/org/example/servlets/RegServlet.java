package org.example.servlets;

import org.example.dao.UsersDAO;
import org.example.model.User;
import org.example.util.EncryptDecryptUtils;
import org.example.util.IOUtils;
import org.example.util.MailUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("reg.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();

        String password1 = EncryptDecryptUtils.encrypt(req.getParameter("password1"));
        String password2 = EncryptDecryptUtils.encrypt(req.getParameter("password2"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("reg.html");
        resp.setContentType("text/html;charset=UTF-8");

        // не совпадают пароль1 и пароль2
        if (!password1.equals(password2)) {

            resp.getWriter().println("Passwords are not equals");
            dispatcher.include(req, resp);
            return;
        }

        // можно доп-но добавить, что пароль слабенький (длина, цифра, буква, спец символ .. )

        // проверить, может пользователь уже ранее зарегистрирован

        UsersDAO dao = new UsersDAO();
        User user = dao.getByEmail(email);

        if(user != null){
            resp.getWriter().println("<b>Email already exist! please</b> <a href='login'>login<a>");
            dispatcher.include(req, resp);
            return;
        }

        // если пользователя нет, нужно создать и добавить в БД

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPsw(password1);
        boolean added = dao.insert(newUser);

        // направим письмо с инструкцией по активизации
        // возьмем перегруженный метод send(), т.к. нужен contentType - html
        // в методе send() с тремя параметрами, простой текст, а мы хотим передать письмом html
        // вместо 4го параметра проставим null
        // контент - ищем готовый код на bootsrtrap - в корне webapp новый пакет templates, новый activation.html
        // чтобы запихнуть весь этот код в String, нужны инстременты IO, прочитай все из файла
        // добавим IOUtils

        if (added) {

            String content = IOUtils.readFileBuff("C:\\Users\\makei\\IdeaProjects\\simple-web-app\\src\\main\\webapp\\templates\\activation.html");
            content = content.replace
                    ("{*}", "http://localhost:8080/simple-web-app/activate?email=" + email);
            MailUtils.send(email, "activation", content, null);

            resp.getWriter().println("Thanks for registration. Check your email-box");
        }

        else {
            resp.getWriter().println("Some error on server side");
            dispatcher.include(req, resp);
        }

    }

 }
