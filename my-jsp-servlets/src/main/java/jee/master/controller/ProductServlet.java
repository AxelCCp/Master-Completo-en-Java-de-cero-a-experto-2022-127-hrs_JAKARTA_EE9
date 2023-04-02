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
import java.util.List;

@WebServlet("/products-servlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = (Connection) req.getAttribute("connection");
        IProductService productService = new ProductService(connection);
        List<Product> products = productService.list();

        req.setAttribute("products", products);
        this.getServletContext().getRequestDispatcher("/list.jsp").forward(req,resp);

    }
}
