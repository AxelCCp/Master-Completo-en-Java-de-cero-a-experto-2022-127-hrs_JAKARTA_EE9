<%@page contentType="text/html"  pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <c:if test="${requestScope.username.isPresent()}">
        <div>Hola <c:out value="${requestScope.username.get()}"/>, bienvenido</div>
        <p><a href="<c:out value="${pageContext.request.contextPath}"/>/productos/form">crear [+]</a></p>
    </c:if>

    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <c:if test="${requestScope.username.isPresent()}">
            <th>precio</th>
            <th>agregar</th>
            <th>editar</th>
            <th>eliminar</th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.productos}" var="p">
        <tr>
            <td><c:out value="${p.id}"/></td>
            <td><c:out value="${p.nombre}"/></td>
            <td><c:out value="${p.categoria.nombre}"/></td>
            <c:if test="${requestScope.username.present}">
            <td><c:out value="${p.precio}"/></td>
            <td><a href="${pageContext.request.contextPath}/carro/agregar?id=<c:out value="${p.id}"/>">Agregar al carro</a></td>
            <td><a href="${pageContext.request.contextPath}/productos/form?id=<c:out value="${p.id}"/>">editar</a></td>
            <td><a onclick="return confirm('Esta seguro que desea eliminar?')" href="${pageContext.request.contextPath}/productos/eliminar?id=<c:out value="${p.id}"/>">eliminar</a></td>
            </c:if>
        </tr>
         </c:forEach>
    </table>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
</body>
</html>