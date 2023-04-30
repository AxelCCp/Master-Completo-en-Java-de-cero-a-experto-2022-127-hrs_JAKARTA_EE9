package jee.master.ejb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.ejb.model.Producto;
import jee.master.ejb.service.A0_ServiceEjb;
import jee.master.ejb.service.A2_ServiceEjbLocal;

import javax.naming.InitialContext;
import java.io.IOException;

//541

//542 : SE AGERGAN MÁS MÉTODOS

@WebServlet("/index2")
public class A1_EjemploServletConLocal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        A2_ServiceEjbLocal service = null;
        A2_ServiceEjbLocal service2 = null;
        try {                                                    // nombre clase                                           nombre interfaz
            InitialContext ctx = new InitialContext();
            service = (A2_ServiceEjbLocal) ctx.lookup("java:global/jee-master-ejb/A2_ServiceEjbLocal!jee.master.ejb.service.A1_EjbServiceLocal");
            service2 = (A2_ServiceEjbLocal) ctx.lookup("java:global/jee-master-ejb/A2_ServiceEjbLocal!jee.master.ejb.service.A1_EjbServiceLocal");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("service es igual a service 2 ? : " + service.equals(service2));

        Producto p = service.crear(new Producto("Uvas"));
        System.out.println("Nuevo produto: " + p);

        req.setAttribute("saludo", service.saludar("Axel"));
        req.setAttribute("saludo2", service2.saludar("Axel"));
        req.setAttribute("listado", service.listar());

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    /*

    @Inject
    private A1_EjbServiceLocal service;

    @Inject
    private A1_EjbServiceLocal service2;

     */
}
