package jee.master;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class A0_410_FormServet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        Boolean habilitar = req.getParameter("habilitar") != null && req.getParameter("habilitar").equals("on");  //SE PONE "ON" PQ EL CHECKBOS DEVUELVE ON O NULL.
        String secreto = req.getParameter("secreto");

        //List<String> errores = new ArrayList<>();
        Map<String,String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username","El username es requerido.");
        }
        if (password == null || password.isBlank()) {
            errores.put("password","El password no puede estar vacío.");
        }
        if (email == null || !email.contains("@")) {
            errores.put("email","El email es requerido y debe tener un formato válido.");
        }
        if (pais == null || pais.equals("") || pais.equals(" ")) {
            errores.put("pais","El país es requesrido");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes","Debes seleccionar al menos un lenguaje.");
        }
        if (roles == null || roles.length == 0) {
            errores.put("role","Debes seleccionar al menos un role.");
        }
        if (idioma == null) {
            errores.put("idioma","Debes almacenar almenos un idioma.");
        }

        if (errores.isEmpty()) {

            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Resultado Formulario</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Resultado Formulario</h1>");

                out.println("<ul>");

                out.println("<li>username: " + username + "</li>");
                out.println("<li>password: " + password + "</li>");
                out.println("<li>email: " + email + "</li>");

                out.println("<li>País: " + pais + "</li>");

                out.println("<li>Lenguajes: <ul>");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("<li>" + lenguaje + "</li>");
                });
                out.println("</ul></li>");

                out.println("<li>Roles: <ul>");
                Arrays.asList(roles).forEach(role -> {
                    out.println("<li>" + role + "</li>");
                });
                out.println("</ul></li>");

                out.println("<li>Idioma: " + idioma + "</li>");
                out.println("<li>Habilitado: " + habilitar + "</li>");
                out.println("<li>Secreto: " + secreto + "</li>");

                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");

            }

        }else{
                //415
                req.setAttribute("errores", errores);
                //PARA REDIRECCIONAR EL REQ Y EL RESP AL JSP SE USA ESTO:
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }
