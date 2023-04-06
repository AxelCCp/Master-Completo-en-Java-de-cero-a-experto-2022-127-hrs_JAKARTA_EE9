package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.master.models.entity.ShoppingCart;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/products/update-cart-servlet")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession mySession = req.getSession();
        if(mySession.getAttribute("shoppingCart") != null){
            log("ESTOY EN EL IF DE UpdateCartServlet");
            ShoppingCart shoppingCart = (ShoppingCart) mySession.getAttribute("shoppingCart");
            this.updateProducts(req, shoppingCart);
            this.updateQuantities(req, shoppingCart);

        }
        resp.sendRedirect(req.getContextPath() + "/products/cart-review-servlet");
    }


    private static void updateProducts(HttpServletRequest request, ShoppingCart shoppingCart) {
        String[] deleteIds = request.getParameterValues("deleteProducts");
        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productsIds = Arrays.asList(deleteIds);
            shoppingCart.removeProducts(productsIds);
        }
    }

    private static void updateQuantities(HttpServletRequest request, ShoppingCart shoppingCart) {
        Enumeration<String> enumer =request.getParameterNames();
        while(enumer.hasMoreElements()){
            String paramName = enumer.nextElement();
            if(paramName.startsWith("q_")) {
                String id = paramName.substring(2);
                String quantity = request.getParameter(paramName);
                if(quantity != null){
                    shoppingCart.updateQuantity(id, Integer.parseInt(quantity));
                }
            }
        }
    }
}
