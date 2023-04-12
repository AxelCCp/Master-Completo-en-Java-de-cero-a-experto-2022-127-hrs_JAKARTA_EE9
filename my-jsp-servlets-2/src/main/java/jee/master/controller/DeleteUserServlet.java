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
import java.util.Optional;

@WebServlet("/users/delete-servlet")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IUserService userService = new UserService(connection);

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch(NumberFormatException e){
            id = 0L;
        }

        if(id > 0){
            Optional<User>userOptional = userService.getUserById(id);
            if(userOptional.isPresent()){
                userService.deleteById(id);
                resp.sendRedirect(req.getContextPath() + "/users/list-servlet");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The user you want to delete does not exist in the database!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The user id is empty. Send this by parameter!");
        }
    }
}
