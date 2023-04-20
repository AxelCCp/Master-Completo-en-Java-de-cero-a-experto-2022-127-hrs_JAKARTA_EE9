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

@WebServlet("/clients/detail-servlet")
public class ClientDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        connection = (Connection) req.getAttribute("connection");
        clientService = new ClientService(connection);

        Long id;

        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Client client = new Client();
        String messageErrorId;

        if(id > 0) {
            Optional<Client> optionalClient = clientService.getClientById(id);
            client = optionalClient.get();
            if(client.getId() != null){
                messageErrorId = " ";
                req.setAttribute("messageErrorId", messageErrorId);
                req.setAttribute("client", client);
                this.getServletContext().getRequestDispatcher("/client-detail.jsp").forward(req,resp);
            } else {
                messageErrorId = "Error, the client with the Id : \"" + id + "\", doesn't exist on the database!";
                req.setAttribute("messageErrorId", messageErrorId);
                resp.sendRedirect(req.getContextPath() + "/clients/list-servlet");
            }
        } else {
            messageErrorId = "Error, the id client \"" + id + "\" is not valid!";
            req.setAttribute("messageErrorId", messageErrorId);
            resp.sendRedirect(req.getContextPath() + "/clients/list-servlet");
        }

    }

    Connection connection;
    IClientService clientService;
}
