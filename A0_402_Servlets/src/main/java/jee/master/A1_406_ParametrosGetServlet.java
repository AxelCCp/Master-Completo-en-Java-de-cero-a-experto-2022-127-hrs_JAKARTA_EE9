package jee.master;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class A1_406_ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre");

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<title>Parametros get de la url</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>Servlet 2 : Parametros get de la url</h1>");

        if(saludo!=null && nombre != null){
            out.println("<h3>El saludo enviado es: " + saludo + " " + nombre +"</h3>");
        } else if (saludo != null) {
            out.print("<h3>El saludo enviado es: " + saludo + "</h3>");

        }else if(nombre != null){
            out.print("<h3>El nombre enviado es: " + nombre + "</h3>");
        }else{
            out.print("<h3>No se han mandado los parámetros saludo ni nombre</h3>");
        }

        try{
            Integer codigo = Integer.valueOf(req.getParameter("codigo"));
            out.println("<h3>El codigo enviado es : " + codigo + "</h3>");
        }catch(NumberFormatException e){
            out.println("<h3>El codigo  no se ha enviado, es null.</h3>");
        }

        out.print("</body>");
        out.print("</html>");
        out.close();
    }
}
