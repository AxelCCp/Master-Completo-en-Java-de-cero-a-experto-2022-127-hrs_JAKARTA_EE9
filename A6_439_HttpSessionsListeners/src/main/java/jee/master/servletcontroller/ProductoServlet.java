package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Producto;
import jee.master.model.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
//440
//1 : SE CAPTURAN LOS DATOS CARGADOS EN LA SESSION DESDE LOS LISTENERS

@WebServlet({"/productos.html"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        ILoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        String mensajeRequest = (String) req.getAttribute("mensaje");               //1
        String mensajeApp = (String) getServletContext().getAttribute("mensaje");

        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {

            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"UTF-8\">");
            out.print("<title>Listado de  productos</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>Listado de  productos</h1>");
            out.println("<br>");
            if(usernameOptional.isPresent()) {
                out.println("<div>Hola... " + usernameOptional.get() + " bienvenido!</div>");
            }
            out.println("<br>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            if(usernameOptional.isPresent()) {
                out.println("<th>precio</th>");
                out.println("<th>agregar</th>");
            }
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href=\""+ req.getContextPath() + "/carro/agregar?id=" + p.getId() + "\">agregar al carro</a></td>");
                }
                out.println("</tr>");
            });

            out.println("</table>");

            out.println("<br>");
            out.println("<p>mensajeApp : " + mensajeApp + "</p>");
            out.println("<p>mensajeRequest :" + mensajeRequest + "</p>");

            out.print("</body>");
            out.print("</html>");

        }
    }
}
