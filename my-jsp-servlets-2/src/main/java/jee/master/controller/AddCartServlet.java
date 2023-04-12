package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.master.models.entity.Item;
import jee.master.models.entity.Product;
import jee.master.models.entity.ShoppingCart;
import jee.master.models.service.IProductService;
import jee.master.models.service.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/products/add-cart-servlet")
public class AddCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        Connection connection = (Connection) req.getAttribute("connection");
        IProductService productService = new ProductService(connection);
        Optional<Product> optionalProduct = productService.getProductById(id);

        if(optionalProduct.isPresent()){
            Item myItem = new Item(optionalProduct.get(), 1);
            HttpSession mySession = req.getSession();
            ShoppingCart shoppingCart = (ShoppingCart) mySession.getAttribute("shoppingCart");
            shoppingCart.addItemCart(myItem);
        }
        resp.sendRedirect(req.getContextPath() + "/products/cart-review-servlet");



    }
}
