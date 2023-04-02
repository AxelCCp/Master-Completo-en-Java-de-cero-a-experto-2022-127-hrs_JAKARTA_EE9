<%@page contentType="UTF-8" import="java.util.*, jee.master.models.entity.*"%>

<%
List<Product>products = (List<Product>)request.getAttribute("products");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product list</title>
</head>
<body>
  <h1>Product list</h1>

  <table>
      <tr>
          <th>id</th>
          <th>sku</th>
          <th>name</th>
          <th>comments</th>
          <th>division</th>
          <th>releaseDate</th>
          <th>puchaseDate</th>
      </tr>
      <% for(Product p : products){%>
      <tr>
          <td><%=p.getId()%></td>
          <td><%=p.getSku()%></td>
          <td><%=p.getName()%></td>
          <td><%=p.getComments()%></td>
          <td><%=p.getDivision().getName()%></td>
          <td><%=p.getReleaseDate()%></td>
          <td><%=p.getPuchaseDate()%></td>
      </tr>
      <%}%>
  </table>


</body>
</html>