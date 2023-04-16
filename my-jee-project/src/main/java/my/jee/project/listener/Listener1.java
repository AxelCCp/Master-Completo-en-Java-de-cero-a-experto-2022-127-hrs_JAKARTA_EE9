package my.jee.project.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class Listener1 implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Initializing the application.");
        servletContext.setAttribute("message", "Developed with Java 17 & Jakarta EE9");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Finishing the application. Context Destroyed.");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("Finishing the application. Request Destroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("Initializing the application. Starting Request.");
        sre.getServletRequest().setAttribute("message", " / version 1 ");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("The session Http was created.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Deleting the Http session.");
    }

    private ServletContext servletContext;
}
