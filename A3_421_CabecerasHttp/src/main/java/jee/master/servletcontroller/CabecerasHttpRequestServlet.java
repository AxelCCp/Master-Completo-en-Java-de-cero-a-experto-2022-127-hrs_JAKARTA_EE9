package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        Integer port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url1 = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        String ip_Cliente = req.getRemoteAddr();

        try(PrintWriter out = resp.getWriter()) {

            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"UTF-8\">");
            out.print("<title>Cabeceras http request</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>Cabeceras http request</h1>");
            out.println("<br>");
            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request Uri: " + requestUri + "</li>");
            out.println("<li>request Url: " + requestUrl + "</li>");
            out.println("<li>context Path: " + contextPath + "</li>");
            out.println("<li>servlet Path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ip_Cliente + "</li>");
            out.println("<li>local port: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>host: " + url1 + "</li>");
            out.println("<li>host: " + url2 + "</li>");

            Enumeration<String>headerNames = req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String cabecera = headerNames.nextElement();
                out.println("<li>cabecera --> " + cabecera + " : " + req.getHeader(cabecera) + "</li>");
            }

            out.println("</ul>");
            out.print("</body>");
            out.print("</html>");

        }
    }
}
