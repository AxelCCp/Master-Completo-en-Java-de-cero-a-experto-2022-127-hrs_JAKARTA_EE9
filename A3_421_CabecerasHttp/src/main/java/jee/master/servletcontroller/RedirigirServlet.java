package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {

    //ESTO SE PARECE AL despachar QUE SE USA EN EL SERVLET DESPACHAR SERVLET. LA DIFERENCIA ESTÁ EN QUE AL REDIRIGIR SE EMITE UNA NUEVA SOLICITUD HTTP. UNA NUEVA REQUEST.
    //MIENTRAS Q CON DESPACHAR NO SE REALIZA UN REQUEST NUEVO. USA EL REQUEST DEL DESPACHAR Y LO UNE AL REQUEST Y RESPONSE DEL RECURSO Q SE VA A DESPACHAR.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //resp.setHeader("Location", req.getContextPath() + "/productos.html");
        //resp.setStatus(HttpServletResponse.SC_FOUND);

        //LAS 2 LÍNEAS ANTERIORES SON LO MISMO QUE ...

        resp.sendRedirect(req.getContextPath() + "/productos.html");
    }
}
