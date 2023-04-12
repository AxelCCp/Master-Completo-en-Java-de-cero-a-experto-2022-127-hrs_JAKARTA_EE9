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
                out.print("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">");
                out.print("</head>");
                out.print("<body style=\"background-color:#95A5A6;\">");
                out.println("<br><br>");
                out.print("<div style=\"margin-left: 100px; margin-right: 100px; color:#D7DBDD;\"><h3>Hi " + optionalUsername.get() + ", you have logged in successfully!</h3></div>");
                out.println("<br>");
                out.println("<div style=\"margin-left: 100px; margin-right: 100px\"><h3><a class=\"link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover\" href='" + req.getContextPath() + "/index.html'>Home</a></h3></div>");
                out.println("<br>");
                out.println("<div style=\"margin-left: 100px; margin-right: 100px\"><h3><a class=\"link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover\" href='" + req.getContextPath() + "/users/logout-servlet'>Logout</a></h3></div>");
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
