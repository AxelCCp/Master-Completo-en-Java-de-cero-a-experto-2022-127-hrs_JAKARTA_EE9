<%@page contentType="text/html"  pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de usuarios</title>
</head>
<body>

    <h1>Lista de usuarios</h1>

    <table>
        <tr>
            <th>id</th>
            <th>username</th>
            <th>email</th>
        </tr>
        <c:forEach items="${requestScope.listaDeUsuarios}" var="u">
            <tr>
                <td><c:out value="${u.id}"/></td>
                <td><c:out value="${u.username}"/></td>
                <td><c:out value="${u.email}"/></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>