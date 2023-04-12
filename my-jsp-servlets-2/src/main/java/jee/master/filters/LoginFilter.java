package jee.master.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.models.service.ILoginService;
import jee.master.models.service.LoginSessionService;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/products/form-servlet","/products/cart-review-servlet","/products/update-cart-servlet","/products/add-cart-servlet", "/products/delete-servlet"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ILoginService loginService = new LoginSessionService();
        Optional<String>userOptional = loginService.getUsername((HttpServletRequest) request);
        if(userOptional.isPresent()){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to enter this page!");
        }
    }
}
