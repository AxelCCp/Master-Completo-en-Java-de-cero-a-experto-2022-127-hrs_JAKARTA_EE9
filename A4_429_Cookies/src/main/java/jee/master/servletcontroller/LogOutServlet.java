package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.service.ILoginService;
import jee.master.model.service.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       ILoginService auth = new LoginServiceImpl();
        Optional<String>username = auth.getUsername(req);
        if(username.isPresent()){
            //SE LE SETTEA UN VALOR VACÍO A LA COOKIE
            Cookie usernameCookie = new Cookie("username", "");
            //HACEMOS QUE LA COOKIE EXPIRE INMEDIATAMENTE
            usernameCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
        }
        resp.sendRedirect(req.getContextPath() + "/Login.html");
    }
}
