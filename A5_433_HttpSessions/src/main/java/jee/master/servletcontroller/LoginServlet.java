package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jee.master.model.service.ILoginService;
import jee.master.model.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ILoginService auth = new LoginServiceSessionImpl();
        Optional<String>usernameOptional = auth.getUsername(req);

        if(usernameOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\">");
                out.print("<title>Hola " + usernameOptional.get() + "</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito!</h1>");
                out.println("<br>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>volver</a></p>");
                out.println("<br>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.print("</body>");
                out.print("</html>");
            }
        }else {
            this.getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);                                             //SE CARGA EL JSP DE LOGIN SI ES QUE LA REQUEST NO TRAE LA COOKIE USERNAME.
        }
    }

    //EN ESTE MÉTODO SE OBTIENEN LOS DATOS  PASSWORD Y USERNAME QUE VIENEN DESDE UN FORMULARIO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){

            HttpSession session = req.getSession();                                                                                     //SE CREA EL OBJ SESSION
            session.setAttribute("username",username);                                                                                  //SE ESTABLECE El ATRIBUTO USERNAME.
            resp.sendRedirect(req.getContextPath() + "/login.html");

        }else{
            //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED , "No está autorizado para ingresar a esta página");
        }
    }

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
}
