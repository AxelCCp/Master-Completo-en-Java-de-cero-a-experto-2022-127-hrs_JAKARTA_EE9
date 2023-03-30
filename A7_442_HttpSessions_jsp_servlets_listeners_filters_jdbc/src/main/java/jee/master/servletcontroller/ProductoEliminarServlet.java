package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Producto;
import jee.master.model.service.IProductoService;
import jee.master.model.service.ProductoServiceJdbc;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IProductoService productoService = new ProductoServiceJdbc(conn);
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if(id > 0){
            Optional<Producto> o = productoService.porid(id);
            if(o.isPresent()){
                productoService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos.html");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El producto que se quiere eliminar, no existe en la base de datos!");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id del producto es null, este se debe mandar como parámetro en la url!");
        }

    }
}
