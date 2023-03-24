package jee.master.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jee.master.model.entity.Carro;

//PARA VER  EL RESULTADO DE ESTO ANDA AL LOG DEL TOMCAT....

//0 : SE INDICA QUE ES UN LISTENER DEL API SERVLET.
//1 : ServletContextListener : PARA MANEJAR EL CONTEXTO GENERAL DE LA APP, PARA INICIALIZAR RECURSOS GLOBALES, CONFIGURACIONES, CONEXIONES A BBDD, Y COSAS EN COMUN PARA TODA LA APP.
//2 : TAMBIEN LISTENER PARA LAS REQUEST.
//3 : TAMBN LISTENER PARA LA SESIÓN.
//4 : SCE : ESTE ES EL EVENTO.
//5 : AQUÍ SOLO SE PUEDE ACCEDER AL OBJ HTTPSESSION. POR EJEMPLO PARA CREAR Y GUARDAR UN OBJ EN LA SESIÓN. AQUÍ SE PUEDE GURDAR POR EJEMPLO EL CARRO DE COMPRAS, DE MANERA GLOBAL.
//6 : SE DEBE INICIALIZAR CUANDO SE INICIALIZA EL CONTEXTO DE LOS SERVLETS.
//440
//7 : SE CREA UN OBJ SINGLETON GLOBAL PARA TODA A LA APLICACIÓN.
//8 : LO MISMO DEL PUNTO 7 , SE PUEDE HACER EN requestInitialized, PERO EL ALCANCE VA A SER DIFERENTE. SOLO SE SE A PODER USAR EN EL REQUEST Y DESPUÉS SE DESTRUYE.

//9 : UN EJEMPLO DE ESTO ES EL CARRO DE COMPRA. EN VEZ DE CREARLO EN EL SERVLET, SE PUEDE CREAR CUANDO SE CREA LA SESSION HTTP.
@WebListener //0                                1                   2                       3
public class AppListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    @Override                                           //4
    public void contextInitialized(ServletContextEvent sce) {
       sce.getServletContext().log("inicializando la aplicación.");
       servletContext = sce.getServletContext();    //6
        servletContext.setAttribute("mensaje", "Algun valor global de la aplicación.");    //7
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
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request.");        //8
    }

    //5
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la sessión http");

        Carro carro = new Carro();                                        //9
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("eliminando la sessión http");
    }

    private ServletContext servletContext;
}
