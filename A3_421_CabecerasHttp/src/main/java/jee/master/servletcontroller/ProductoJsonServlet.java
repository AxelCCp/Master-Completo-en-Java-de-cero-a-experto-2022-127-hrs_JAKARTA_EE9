package jee.master.servletcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Producto;
import jee.master.model.service.IProductoService;
import jee.master.model.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductoService service = new ProductoServiceImpl();
        List<Producto>productos  = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        //SE CREA UN JSON DE STRING
        String json = mapper.writeValueAsString(productos);
        //SE ESTABLECE EL TIPO DE LA RESPUESTA
        resp.setContentType("application/json");
        //SE PASA AL CUERPO DE LA RESPUESTA
        resp.getWriter().write(json);
    }

    //A ESTE METODO LE MANDAS UNA REQUEST POST CON UN JSON DE PRODUCTO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream jsonStream = req.getInputStream();                                                                   //SE USA EL DOGET PARA RECIBIR UN OBJ DEL CUERPO DE LA REQUEST Q LLEGA. PARA ESTO SE USA GET INPUT STREAM.
        ObjectMapper mapper = new ObjectMapper();                                                                                //PARA PASAR EL JSON A OBJ O AL REVES.
        Producto producto = mapper.readValue(jsonStream, Producto.class);                                                         //LUEGO EL JSONSTREAM SE PASA A UN OBJ PRODUTO.

        //resp.setContentType("text/html");

        try(PrintWriter out = resp.getWriter()) {
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"UTF-8\">");
            out.print("<title>Detalle de producto desde Json</title>");
            out.println("<br>");
            out.println("<ul>");
            out.println("<li> id:" + producto.getId() + "</li>");
            out.println("<li> id:" + producto.getNombre() + "</li>");
            out.println("<li> id:" + producto.getTipo() + "</li>");
            out.println("<li> id:" + producto.getPrecio() + "</li>");
            out.println("</ul>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>Detalle de producto desde Json</h1>");
            out.print("</body>");
            out.print("</html>");
        }
    }
}
