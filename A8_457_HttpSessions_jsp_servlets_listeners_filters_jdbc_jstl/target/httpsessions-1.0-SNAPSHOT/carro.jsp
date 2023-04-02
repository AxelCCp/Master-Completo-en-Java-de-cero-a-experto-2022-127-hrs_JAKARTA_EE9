<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
  <h1>Carro de compras</h1>

  <c:choose>
    <c:when test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">                             <%--<c:if test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}"> --%>
        <p>Lo sentimos, no hay productos en el carro.</p>
    </c:when>

  <c:otherwise>
    <form action="${pageContext.request.contextPath}/carro/actualizar" method="post">               <%-- <form action="/httpsessions/carro/actualizar" method="post"> --%>
        <table>
            <tr>
                <th>id</th>
                 <th>nombre</th>
                  <th>precio</th>
                   <th>cantidad</th>
                    <th>total</th>
            </tr>

      
            <c:forEach items="${sessionScope.carro.items}" var="item">
            <tr>
                <td>${item.producto.id}</td>
                <td>${item.producto.nombre}</td>
                <td>${item.producto.precio}</td>
                <td>${item.cantidad}</td>
                <td>${item.importe}</td>
            </tr>
            </c:forEach>

            <tr>
                <td colspan="4" style="text-align: right">Total</td>
                <td>${sessionScope.carro.total}</td>
            </tr>
        </table>
    </form>
  </c:otherwise>
  </c:choose>
    <p><a href="${pageContext.request.contextPath}/productos.html">seguir comprando</a></p>
    <p><a href="${pageContext.request.contextPath}/index.html">volver</a></p>
</body>
</html>