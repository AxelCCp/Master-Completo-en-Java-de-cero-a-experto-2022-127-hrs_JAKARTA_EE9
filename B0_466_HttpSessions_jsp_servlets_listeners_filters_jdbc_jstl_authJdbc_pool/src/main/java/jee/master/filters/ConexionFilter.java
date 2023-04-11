package jee.master.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.service.ServiceJdbcException;
import jee.master.model.util.ConexionBaseDeDatos;
import jee.master.model.util.ConexionBaseDeDatosDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//443

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try{
            Connection conn = ConexionBaseDeDatosDataSource.getConnection();
            System.out.println("conn.getClass().getName() ::::::::::::::::" + conn.getClass().getName());

            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }

            try{
                request.setAttribute("conn", conn);
                chain.doFilter(request,response);
                conn.commit();
            }catch(SQLException | ServiceJdbcException e2){
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e2.getMessage());
                e2.printStackTrace();
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
