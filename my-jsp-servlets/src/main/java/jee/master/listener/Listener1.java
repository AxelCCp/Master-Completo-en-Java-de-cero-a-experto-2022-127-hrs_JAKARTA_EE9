package jee.master.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jee.master.models.entity.ShoppingCart;

@WebListener
public class Listener1 implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Initializing the application...");
        servletContext = sce.getServletContext();    //6
        servletContext.setAttribute("message", "This application is developed with Jakarta EE9.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("...Finishing the application. ...Context Destroyed.");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("...Finishing the application. ...Request Destroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("Initializing the application... Starting Request...");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ShoppingCart shoppingCart = new ShoppingCart();
        HttpSession mySession = se.getSession();
        mySession.setAttribute("shoppingCart", shoppingCart);
        servletContext.log("The session Http was created. ShoppingCart is a new attribute.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Deleting the session Http.");
    }

    private ServletContext servletContext;
}

