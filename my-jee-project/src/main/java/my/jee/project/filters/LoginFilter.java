package my.jee.project.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import my.jee.project.headers.util.DataBaseConectionBasic;
import my.jee.project.models.service.MyExceptionJdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Connection connection = DataBaseConectionBasic.getConnection();
            System.out.println("connection.getClass().getName() :" + connection.getClass().getName());
            if(connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try{
                request.setAttribute("connection", connection);
                chain.doFilter(request,response);
                connection.commit();
            } catch(SQLException | MyExceptionJdbc e) {
                connection.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
