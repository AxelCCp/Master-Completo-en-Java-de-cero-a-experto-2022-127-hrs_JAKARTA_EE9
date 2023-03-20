package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/despachar")
public class DespacharServlet extends HttpServlet {

    //ESTO SE PARECE AL REDIRECT QUE SE USA EN EL SERVLET REDIRIGIR SERVLET. LA DIFERENCIA ESTÁ EN QUE AL REDIRIGIR SE EMITE UNA NUEVA SOLICITUD HTTP. UNA NUEVA REQUEST.
    //MIENTRAS Q CON DESPACHAR NO SE REALIZA UN REQUEST NUEVO. USA EL REQUEST DEL DESPACHAR Y LO UNE AL REQUEST Y RESPONSE DEL RECURSO Q SE VA A DESPACHAR.

    //OTRA COSA DEL DISPATCHER FORWARD ... TODOS LOS PARÁMETROS DEL HTTP , DE LAS CABECERAS, ETC, SE MANTIENEN, YA QUE ES LA MISMA PETICION HTTP.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/productos.html").forward(req,resp);

    }
}
