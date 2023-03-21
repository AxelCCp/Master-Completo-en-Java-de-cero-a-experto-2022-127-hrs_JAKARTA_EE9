package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Producto;
import jee.master.model.service.ILoginService;
import jee.master.model.service.IProductoService;
import jee.master.model.service.LoginServiceImpl;
import jee.master.model.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        ILoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

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
            if(cookieOptional.isPresent()) {
                out.println("<div>Hola... " + cookieOptional.get() + " bienvenido!</div>");
            }
            out.println("<br>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            if(cookieOptional.isPresent()) {
                out.println("<th>precio</th>");
            }
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if(cookieOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                }
                out.println("</tr>");
            });

            out.println("</table>");

            out.print("</body>");
            out.print("</html>");

        }
    }
}
