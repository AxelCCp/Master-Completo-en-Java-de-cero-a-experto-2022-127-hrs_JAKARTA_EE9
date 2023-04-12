package jee.master.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.entity.Division;
import jee.master.models.entity.Product;
import jee.master.models.service.DivisionService;
import jee.master.models.service.IDivisionService;
import jee.master.models.service.IProductService;
import jee.master.models.service.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products/form-servlet")
public class ProductFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IDivisionService divisionService = new DivisionService(connection);
        IProductService productService = new ProductService(connection);

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        Product product = new Product();
        product.setDivision(new Division());
        if(id > 0 ){
            Optional<Product> o = productService.getProductById(id);
            if(o.isPresent()){
                product = o.get();
            }
        }

        req.setAttribute("divisionsList", divisionService.divisionsList());
        req.setAttribute("product", product);
        this.getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection)req.getAttribute("connection");
        IDivisionService divisionService = new DivisionService(connection);
        IProductService productService = new ProductService(connection);

        String sku = req.getParameter("sku");
        String name = req.getParameter("name");
        String comments = req.getParameter("comments");

        Long divisionId;
        try{
            divisionId = Long.valueOf(req.getParameter("division"));
        }catch (NumberFormatException e) {
            divisionId = 0L;
        }

        String releaseDate = req.getParameter("releaseDate");

        String puchaseDate = req.getParameter("puchaseDate");


        Integer price;
        try{
            price = Integer.valueOf(req.getParameter("price"));
        }catch (NumberFormatException e){
            price = 0;
        }

        Map<String, String> errors = new HashMap<>();

        if(sku == null || sku.isBlank()){
            errors.put("sku", "The sku is required!");
        }else if(sku.length() > 10){
            errors.put("sku", "The sku must have a maximum of 10 characters!");
        }

        if(name == null || name.isBlank()){
            errors.put("name", "The name is required!");
        }

        if(comments == null || comments.isBlank()){
            errors.put("comments", "The comments are required!");
        }

        if(price.equals(0)){
            errors.put("price", "The price is required!");
        }

        if(divisionId.equals(0L)){
            errors.put("division", "The division is required!");
        }

        if(releaseDate == null || releaseDate.isBlank()){
            errors.put("releaseDate", "The release date is required!");
        }

        if(puchaseDate == null || puchaseDate.isBlank()){
            errors.put("puchaseDate", "The purchase date is required!");
        }

        LocalDate date1;
        try {
            date1 = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            date1 = null;
        }

        LocalDate date2;
        try {
            date2 = LocalDate.parse(puchaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            date2 = null;
        }

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Product product = new Product();
        product.setId(id);
        product.setSku(sku);
        product.setName(name);
        product.setComments(comments);
        Division division = new Division();
        division.setId(divisionId);
        product.setDivision(division);
        product.setReleaseDate(date1);
        product.setPuchaseDate(date2);
        product.setPrice(price);

        if(errors.isEmpty()) {
            productService.saveProduct(product);
            resp.sendRedirect(req.getContextPath() + "/products/list-servlet");
        }else {
            req.setAttribute("errors", errors);
            req.setAttribute("divisionsList", divisionService.divisionsList());
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
