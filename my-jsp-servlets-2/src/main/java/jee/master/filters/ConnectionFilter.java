package jee.master.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.connection.DataBaseAccess;
import jee.master.models.connection.DataBaseAccessWithPool;
import jee.master.models.service.ExceptionJdbcService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            Connection connection = DataBaseAccessWithPool.getConnection();
            System.out.println("connection.getClass().getName() ::::::::::::::::" + connection.getClass().getName());

            if(connection.getAutoCommit()) connection.setAutoCommit(false);

            try{
                request.setAttribute("connection", connection);
                chain.doFilter(request,response);
                connection.commit();
            } catch (SQLException | ExceptionJdbcService e2) {
                connection.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e2.getMessage());
                e2.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

