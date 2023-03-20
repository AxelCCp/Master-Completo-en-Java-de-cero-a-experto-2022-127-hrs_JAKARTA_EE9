package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Producto;
import jee.master.model.service.IProductoService;
import jee.master.model.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html");
        String servletPath = req.getServletPath();
        Boolean esXls = servletPath.endsWith(".xls");

        if(esXls == true){
            resp.setContentType("application/vnd.ms.excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }

        try(PrintWriter out = resp.getWriter()) {

            if(esXls == false){
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\">");
                out.print("<title>Listado de  productos</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>Listado de  productos</h1>");
                out.println("<br>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
                out.println("<br>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar json</a></p>");
            }

            out.println("<table>");
                out.println("<tr>");
                    out.println("<th>id</th>");
                    out.println("<th>nombre</th>");
                    out.println("<th>tipo</th>");
                    out.println("<th>precio</th>");
                out.println("</tr>");

                productos.forEach(p -> {
                    out.println("<tr>");
                    out.println("<td>" + p.getId() +"</td>");
                    out.println("<td>" + p.getNombre() +"</td>");
                    out.println("<td>" + p.getTipo() +"</td>");
                    out.println("<td>" + p.getPrecio() +"</td>");
                    out.println("</tr>");
                });

            out.println("</table>");

            if(esXls == false) {
                out.print("</body>");
                out.print("</html>");
            }
        }
    }
}
