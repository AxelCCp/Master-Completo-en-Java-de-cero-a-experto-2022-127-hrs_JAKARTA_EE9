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
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IProductoService service = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");
        Optional<Producto> encontrado = service.listar().stream().filter(p -> {
            if(nombre.isBlank() || nombre == null){
                return false;
            }
            return p.getNombre().contains(nombre);
        }).findFirst();

        if(encontrado.isPresent()){

            resp.setContentType("text/html;charset=UTF-8");

            try(PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\">");
                out.print("<title>Producto encontrado</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>Producto encontrado</h1>");
                out.print("<h3>Producto encontrado:  " + encontrado.get().getNombre() + " - Precio: " + encontrado.get().getPrecio() + "</h3>");
                out.print("</body>");
                out.print("</html>");
            }

        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el producto ingresado ... " + nombre);
        }
    }
}
