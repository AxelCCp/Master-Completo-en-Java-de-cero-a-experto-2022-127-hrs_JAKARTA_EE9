<%@page contentType="UTF-8" import="java.util.*, my.jee.project.models.entity.*"%>

<%
List<Client>clients = (List<Client>)request.getAttribute("clients");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Clients list</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body style="background-color:#CACFD2;">

<div style="margin-left: 100px; margin-right: 100px">


    <br><br>

<h2 style="color:#2471A3;">Clients List</h2>

<a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/clients/form-servlet">Register new client [+]</a>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Lastname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Date of registry</th>
        <th>Detail</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">

    <%for(Client c : clients){%>
    <tr>
        <th><%= c.getId() %></th>
        <td><%= c.getName() %></td>
        <td><%= c.getLastName1() %></td>
        <td><%= c.getLastName2() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getRegistry() %></td>
        <td><a class="btn btn-sm btn-info" href="<%=request.getContextPath()%>/clients/detail-servlet?id=<%= c.getId() %>">Detail</a></td>
        <td><a class="btn btn-sm btn-light" href="<%=request.getContextPath()%>/clients/form-servlet?id=<%= c.getId() %>">Update</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('esta seguro que desea eliminar?');" href="<%=request.getContextPath()%>/clients/delete-servlet?id=<%= c.getId() %>">X</a></td>
    </tr>
    <%}%>

    </tbody>
</table>

</div>

</body>
</html>