package jee.master.servletcontroller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.master.model.entity.Carro;
import jee.master.model.entity.ItemCarro;
import jee.master.model.entity.Producto;
import jee.master.model.service.IProductoService;
import jee.master.model.service.ProductoServiceImpl;
import jee.master.model.service.ProductoServiceJdbc;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));                            //OBTENER EL PRODUCTO

        Connection conn = (Connection)req.getAttribute("conn");
        IProductoService productoService = new ProductoServiceJdbc(conn);

        Optional<Producto> producto = productoService.porid(id);

        if(producto.isPresent()){

            ItemCarro item = new ItemCarro(1,producto.get());

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

    @Inject
    private Carro carro;
}
