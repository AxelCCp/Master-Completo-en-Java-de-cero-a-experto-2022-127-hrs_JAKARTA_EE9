package jee.master.ejb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.ejb.service.A0_ServiceEjb;

import javax.naming.InitialContext;
import java.io.IOException;

//538

//539
    //1 - CON @EJB CON SE INYECTA EL SERVICE CUANDO ES @STATELESS
    //, 2, 3,
    //4 - CON @INJECT SE INYECTA LA INSTANCIA DEL SERVICE CUANDO ESTE ESTA CON @STATEFUL Y @RequestScoped.

//540 :
    //5 - SE HACE LO MISMO Q EN LA CLASE ANTERIOR, PERO DE FORMA PROGRAMATICA. SE ESTA MANERA, EL PROGRAMA NO SA LAS ANOTACIONES DE CDI.

@WebServlet("/index")
public class A0_EjemploServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //5...
        A0_ServiceEjb service = null;
        A0_ServiceEjb service2 = null;
        try {                                                              // nombre clase                       nombre clase
            InitialContext ctx = new InitialContext();
            service = (A0_ServiceEjb) ctx.lookup("java:global/jee-master-ejb/A0_ServiceEjb!jee.master.ejb.service.A0_ServiceEjb");
            service2 = (A0_ServiceEjb) ctx.lookup("java:global/jee-master-ejb/A0_ServiceEjb!jee.master.ejb.service.A0_ServiceEjb");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //...5

        System.out.println("service es igual a service 2 ? : " + service.equals(service2)); //2

        req.setAttribute("saludo", service.saludar("Axel"));

        req.setAttribute("saludo2", service2.saludar("Axel"));//3

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    /*

    //@EJB      //1
    @Inject     //4
    private ServiceEjb service;

    //@EJB
    @Inject
    private ServiceEjb service2;

     */
}
