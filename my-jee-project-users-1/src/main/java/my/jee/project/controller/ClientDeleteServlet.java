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
import java.util.Optional;

@WebServlet("/clients/delete-servlet")
public class ClientDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        connection = (Connection) req.getAttribute("connection");
        clientService = new ClientService(connection);
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }

        if(id > 0) {
            Optional<Client>client = clientService.getClientById(id);
            if(client.isPresent()){
                clientService.deleteClientById(id);
                resp.sendRedirect(req.getContextPath() + "/clients/list-servlet");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The client you want to delete does not exist in the database!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The client id is empty. Send this by parameter!");
        }
    }

    Connection connection;
    IClientService clientService;
}
