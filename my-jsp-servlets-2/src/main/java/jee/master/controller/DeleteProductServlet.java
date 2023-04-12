package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.entity.Product;
import jee.master.models.service.IProductService;
import jee.master.models.service.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/products/delete-servlet")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IProductService productService = new ProductService(connection);

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch(NumberFormatException e){
            id = 0L;
        }

        if(id > 0){
            Optional<Product> optionalProduct = productService.getProductById(id);
            if(optionalProduct.isPresent()){
                productService.deleteById(id);
                resp.sendRedirect(req.getContextPath() + "/products/list-servlet");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The product you want to delete does not exist in the database!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The product id is empty. Send this by parameter!");
        }
    }


}
