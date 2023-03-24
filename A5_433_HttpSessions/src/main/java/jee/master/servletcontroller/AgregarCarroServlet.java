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

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));                            //OBTENER EL PRODUCTO

        IProductoService productoService = new ProductoServiceImpl();

        Optional<Producto> producto = productoService.porid(id);

        if(producto.isPresent()){

            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro;

            if(session.getAttribute("carro") == null){
                carro = new Carro();
                session.setAttribute("carro",carro);
            }else{
                carro = (Carro) session.getAttribute("carro");
            }

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
