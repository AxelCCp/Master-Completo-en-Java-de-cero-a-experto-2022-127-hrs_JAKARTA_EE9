package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.master.models.entity.User;
import jee.master.models.service.ILoginService;
import jee.master.models.service.IUserService;
import jee.master.models.service.LoginSessionService;
import jee.master.models.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/users/login-servlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILoginService loginService = new LoginSessionService();
        Optional<String>optionalUsername =loginService.getUsername(req);

        if(optionalUsername.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\">");
                out.print("<title>Hello! " + optionalUsername.get() + "</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>Hi " + optionalUsername.get() + ", you have logged in successfully!</h1>");
                out.println("<br>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Return home</a></p>");
                out.println("<br>");
                out.println("<p><a href='" + req.getContextPath() + "/users/logout-servlet'>Logout</a></p>");
                out.print("</body>");
                out.print("</html>");
            }
        }else {
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        IUserService userService = new UserService((Connection) req.getAttribute("connection"));
        Optional<User>userOptional = userService.login(username, password);

        if(userOptional.isPresent()){
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            resp.sendRedirect(req.getContextPath() + "/users/login-servlet");

        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED , "No está autorizado para ingresar a esta página");
        }
    }
}
