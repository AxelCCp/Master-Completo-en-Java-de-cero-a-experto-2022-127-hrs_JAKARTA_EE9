package my.jee.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.jee.project.models.entity.Client;
import my.jee.project.models.service.ClientService;
import my.jee.project.models.service.IClientService;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/clients/form-servlet")
public class ClientFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connection = (Connection) req.getAttribute("connection");
        clientService = new ClientService(connection);
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Client client = new Client();
        if(id > 0) {
            Optional<Client> clientOptional = clientService.getClientById(id);
            client = clientOptional.get();
        }
        req.setAttribute("client", client);
        this.getServletContext().getRequestDispatcher("/client-form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection)req.getAttribute("connection");
        IClientService clientService = new ClientService(connection);
        String name = req.getParameter("name");
        String lastName1 = req.getParameter("lastname1");
        String lastName2 = req.getParameter("lastname2");
        String email = req.getParameter("email");
        String registry = req.getParameter("registry");

        Map<String, String> errors = new HashMap<>();

        if(name == null || name.isBlank()){
            errors.put("name", "The name is required!");
        }

        if(lastName1 == null || lastName1.isBlank()){
            errors.put("lastname1", "The lastname is required!");
        }

        if(lastName2 == null || lastName2.isBlank()){
            errors.put("lastname2", "The lastname is required!");
        }

        if(email == null || email.isBlank()){
            errors.put("email", "The email is required!");
        }

        if(registry == null || registry.isBlank()){
            errors.put("registry", "The registry date is required!");
        }

        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        LocalDate date1;
        try {
            date1 = LocalDate.parse(registry, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            date1 = null;
        }

        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setLastName1(lastName1);
        client.setLastName2(lastName2);
        client.setEmail(email);
        client.setRegistry(date1);

        if(errors.isEmpty()) {
            clientService.saveClient(client);
            resp.sendRedirect(req.getContextPath() + "/clients/list-servlet");
        }else {
            req.setAttribute("errors", errors);
            req.setAttribute("client" , client);
            getServletContext().getRequestDispatcher("/client-form.jsp").forward(req, resp);
        }
    }

    private Connection connection;
    private IClientService clientService;
}
