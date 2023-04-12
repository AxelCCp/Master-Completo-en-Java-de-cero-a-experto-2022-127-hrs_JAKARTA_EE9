package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.entity.User;
import jee.master.models.service.IUserService;
import jee.master.models.service.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/users/form-servlet")
public class FormUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IUserService userService = new UserService(connection);

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        User user = new User();
        if(id > 0){
            Optional<User> o = userService.getUserById(id);
            if(o.isPresent()){
                user = o.get();
            }
        }

        req.setAttribute("user", user);
        this.getServletContext().getRequestDispatcher("/user-form.jsp").forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection)req.getAttribute("connection");
        IUserService userService = new UserService(connection);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Map<String, String> errors = new HashMap<>();

        if(username == null || username.isBlank()){
            errors.put("username", "The username is required!");
        }

        if(password == null || password.isBlank()){
            errors.put("password", "The password is required!");
        }

        if(email == null || email.isBlank()){
            errors.put("email", "The email is required!");
        }

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if(errors.isEmpty()) {
            userService.saveUser(user);
            resp.sendRedirect(req.getContextPath() + "/users/list-servlet");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/user-form.jsp").forward(req, resp);
        }
    }
}
