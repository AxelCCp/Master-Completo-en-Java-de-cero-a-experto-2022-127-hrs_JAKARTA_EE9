package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jee.master.model.service.ILoginService;
import jee.master.model.service.LoginServiceCookieImpl;
import jee.master.model.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       ILoginService auth = new LoginServiceSessionImpl();
        Optional<String>username = auth.getUsername(req);
        if(username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/Login.jsp");
    }
}
