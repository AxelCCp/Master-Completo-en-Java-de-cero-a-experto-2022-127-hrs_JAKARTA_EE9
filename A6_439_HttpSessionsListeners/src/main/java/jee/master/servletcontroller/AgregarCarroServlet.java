package jee.master.servletcontroller;

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

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));                            //OBTENER EL PRODUCTO

        IProductoService productoService = new ProductoServiceImpl();

        Optional<Producto> producto = productoService.porid(id);

        if(producto.isPresent()){

            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();

            //CLASE 440 : SE HIZO UNA MODIFICACIÓN AQUÍ. SE QUITÓ IF. SE QUITÓ LA CREACIÓN DEL CARRO DE COMPRAS Y SE MANDÓ AL LISTENER.
            Carro carro = (Carro) session.getAttribute("carro");

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
