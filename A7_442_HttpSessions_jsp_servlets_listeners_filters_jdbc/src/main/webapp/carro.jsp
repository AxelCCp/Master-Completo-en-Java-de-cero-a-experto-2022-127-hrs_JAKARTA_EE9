<%@page contentType="text/html" pageEncoding="UTF-8" import="jee.master.model.entity.*" %>

<%
Carro carro = (Carro)session.getAttribute("carro");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
  <h1>Carro de compras</h1>

  <% if(carro == null || carro.getItems().isEmpty()){ %>
    <p>Lo sentimos, no hay productos en el carro.</p>

  <% }else { %>

  <form action="/httpsessions/carro/actualizar" method="post">
    <table>
        <tr>
            <th>id</th>
             <th>nombre</th>
              <th>precio</th>
               <th>cantidad</th>
                <th>total</th>
        </tr>

        <% for(ItemCarro item : carro.getItems()){ %>

        <tr>
            <td><%= item.getProducto().getId() %></td>
            <td><%= item.getProducto().getNombre() %></td>
            <td><%= item.getProducto().getPrecio() %></td>
            <td><%= item.getCantidad() %></td>
            <td><%= item.getImporte() %></td>
        </tr>
        <% } %>

        <tr>
            <td colspan="4" style="text-align: right">Total</td>
            <td><%= carro.getTotal() %></td>
        </tr>
    </table>

    </form>
  <% } %>
    <p><a href="<%= request.getContextPath()%>/productos.html">seguir comprando</a></p>
    <p><a href="<%= request.getContextPath()%>/index.html">volver</a></p>
</body>
</html>