package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.entity.User;
import jee.master.models.service.ILoginService;
import jee.master.models.service.IUserService;
import jee.master.models.service.LoginSessionService;
import jee.master.models.service.UserService;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/list-servlet")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = (Connection) req.getAttribute("connection");
        IUserService userService = new UserService(connection);
        List<User> userList = userService.userList();
        req.setAttribute("userList", userList);

        ILoginService login = new LoginSessionService();
        Optional<String> usernameOptional = login.getUsername(req);
        req.setAttribute("username", usernameOptional);

        this.getServletContext().getRequestDispatcher("/userlist.jsp").forward(req,resp);
    }
}
