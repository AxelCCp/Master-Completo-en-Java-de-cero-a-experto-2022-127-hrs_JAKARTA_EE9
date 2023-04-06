package jee.master.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.service.ILoginService;
import jee.master.model.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

//441
//METODOS DISPONIBLES PARA SOBREESCRIBIR:
//1 : DOFILTER() : SE EJECUTA POR CADA REQUEST. POR CADA HILO QUE SE CONECTA A NUESTRA APP. ESTE METODO EJECUTA UNA TAREA ANTES O DESPUES DEL MÉTODO DEL SERVLET doget dopost. ESTE METODO ESTA ORIENTADO SIEMPRE A UNA PETICIÓN HTTP, UNA REQUEST. SE LLAMA CADA VEZ QUE SE INICIALIZA Y FINALIZA UNA REQUEST.
//2 : INIT() : SE USA CUANDO SE CREA O INICIALIZA EL FILTRO. SE EJECUTA UNA SOLAVEZ POR CADA FILTRO QUE TENGAMOS.
//3 : destroy() : SE EJECUTA AL FINAL DEL SICLO DE VIDA.CUANDO SE DESTRUYE EL FILTER.

//4 : SE INDICAN LAS RUTAS DONDE SE APLICARÁ EL FIlTRO. PUEDE PONER LAS RUTAS COMPLETAS DE CADA PAGINA O HACER LO MISMO QUE CON GATEWAY... METIENDOLE *
//5 : LO QUE SE VA A HACER ES QUE LAS RUTAS INDICADAS SEAN PRIVADAS.
//6 : SI EL USERNAME ESTÁ PRESENTE, SE CONTINUA CON LA REQUEST, PARA ESTO ES EL CHAIN, QUE UNE LA REQUEST CON EL RESPONSE.
//7 : SI NO ESTÁ PRESENTE, SE MANNDA UN ERROR.
@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*"})                    //4
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //5
        ILoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if(username.isPresent()){                       //6
            chain.doFilter(request,response);
        }else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "No está authorizado para ingresar a esta página.");
        }

    }

}
