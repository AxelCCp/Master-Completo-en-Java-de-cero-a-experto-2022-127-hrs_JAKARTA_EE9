package org.aguzman.webapp.ear.war.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.webapp.ear.service.UsuarioService;

import java.io.IOException;


//1 - SE INYECTA EL EJB CON CDI. ES IMPORTANTE USA @Inject o no @EJB, YA QUE EL @EJB NO VA A TOMAR EN CUENTA EL CONTEXTO CDI DEL REQUEST O LOS CONTEXTOS CDI Q SE ESTÉN USANDO. MIENTRAS Q EL @Inject SI TOMA ESTOS DATOS.

@WebServlet({"/listar", "/"})
public class UsuarioServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usuarios", service.listar());
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
