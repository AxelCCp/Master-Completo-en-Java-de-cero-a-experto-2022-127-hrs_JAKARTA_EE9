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
import java.util.List;

@WebServlet("/clients/list-servlet")
public class ClientListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        connection = (Connection) req.getAttribute("connection");
        clientService = new ClientService(connection);
        List<Client> clients = clientService.listaClientes();
        req.setAttribute("clients",clients);
        this.getServletContext().getRequestDispatcher("/clients-list.jsp").forward(req,resp);
    }


    Connection connection;
    IClientService clientService;
}
