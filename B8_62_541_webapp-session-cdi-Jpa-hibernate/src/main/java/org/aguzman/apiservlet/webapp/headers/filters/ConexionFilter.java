package org.aguzman.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.configs.MySqlConnPrincipal;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;
import org.aguzman.apiservlet.webapp.headers.util.ConexionBaseDatosDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //try (Connection connRequest = this.conn) {            //SE CAMBIA EL TRY CON RECURSOS () A UN TRY SIN RECURSOS. ESTO PARA NO MANEJAR ACA EL CIERRE DE CONEXION. CON ESTO EL FILTRO SOLO QUEDA PARA MANEJO DE TRANSACCIONES.

        /*  479 SE QUITA CONEXION PQ AHORA SE USA TRANSACTIONAL INTERCEPTOR.
        try {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }
         */

            try {
                //request.setAttribute("conn", connRequest);
                chain.doFilter(request, response);
                //connRequest.commit();
            } catch (/*SQLException |*/ ServiceJdbcException e) {
                //connRequest.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        /*   479 SE QUITA CONEXION PQ AHORA SE USA TRANSACTIONAL INTERCEPTOR.
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        */
    }

    /*     479 SE QUITA CONEXION PQ AHORA SE USA TRANSACTIONAL INTERCEPTOR.
    @Inject
    @MySqlConnPrincipal         //@Named("conn")
    private Connection conn;
    */

}
