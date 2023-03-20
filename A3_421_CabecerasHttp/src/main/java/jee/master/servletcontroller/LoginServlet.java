package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //EN ESTE MÉTODO SE OBTIENEN LOS DATOS  PASSWORD Y USERNAME QUE VIENEN DESDE UN FORMULARIO

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){

            resp.setContentType("text/html;charset=UTF-8");

            try(PrintWriter out = resp.getWriter()) {

                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\">");
                out.print("<title>Login correcto</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>Login correcto</h1>");
                out.println("<br>");
                out.println("<h3>Hola " +  username + " has iniciado sesión con éxito!</h3>");
                out.print("</body>");
                out.print("</html>");
            }

        }else{
            //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED , "No está autorizado para ingresar a esta página");
        }
    }

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
}
