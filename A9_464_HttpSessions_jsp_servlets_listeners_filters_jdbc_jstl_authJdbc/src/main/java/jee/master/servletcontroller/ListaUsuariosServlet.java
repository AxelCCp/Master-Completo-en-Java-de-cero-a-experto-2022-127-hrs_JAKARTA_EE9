package jee.master.servletcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jee.master.model.entity.Usuario;
import jee.master.model.service.IUsuarioService;
import jee.master.model.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuarios/lista")
public class ListaUsuariosServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = (Connection) req.getAttribute("conn");
        usuarioService = new UsuarioServiceImpl(connection);
        List<Usuario>listaDeUsuarios = usuarioService.userList();
        req.setAttribute("listaDeUsuarios", listaDeUsuarios);
        getServletContext().getRequestDispatcher("/listarUsuarios.jsp").forward(req,resp);
    }

    IUsuarioService usuarioService;

}
