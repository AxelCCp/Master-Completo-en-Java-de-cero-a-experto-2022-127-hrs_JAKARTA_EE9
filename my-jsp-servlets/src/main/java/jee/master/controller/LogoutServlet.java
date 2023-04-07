package jee.master.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.master.models.service.ILoginService;
import jee.master.models.service.LoginSessionService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/users/logout-servlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ILoginService login = new LoginSessionService();
        Optional<String> optionalUsername = login.getUsername(req);
        if(optionalUsername.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }


}
