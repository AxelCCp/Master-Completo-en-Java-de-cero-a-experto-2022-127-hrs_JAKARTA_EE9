package jee.master.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jee.master.model.entity.Carro;

//LOS COMENTARIOS Y PARTE DEL CÓDIGO SE MODIFICARON EN LA CLASE 469

//469
//1.-SE QUITÓ EL CÓDIGO Q CREA LA SESSION CON EL CARRO YA QUE AHORA SE MANEJA CON ANOTACIONES EN EL ENTITY.


@WebListener
public class AppListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       sce.getServletContext().log("inicializando la aplicación.");
       servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Algun valor global de la aplicación.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("finalizando la aplicación.");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("finalizando la aplicación.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("inicializando la request.");
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request.");
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la sessión http");

        //1
        //Carro carro = new Carro();
        //HttpSession session = se.getSession();
        //session.setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("eliminando la sessión http");
    }

    private ServletContext servletContext;
}
