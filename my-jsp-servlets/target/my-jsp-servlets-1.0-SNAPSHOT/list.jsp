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

  <p><a href="<%=request.getContextPath()%>/products/form-servlet">+ Create a new registry</a></p>

  <table>
      <tr>
          <th>Id</th>
          <th>Sku</th>
          <th>Name</th>
          <th>Comments</th>
          <th>Division</th>
          <th>Release date</th>
          <th>Purchase date</th>
          <th>Price</th>
          <th>add to cart</th>
          <th>Update</th>
          <th>Delete</th>
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
          <td><%=p.getPrice()%></td>
          <td><a href="<%=request.getContextPath()%>/products/add-cart-servlet?id=<%=p.getId()%>">add to cart</a></td>
          <td><a href="<%=request.getContextPath()%>/products/form-servlet?id=<%=p.getId()%>">Update</a></td>
          <td><a onclick="return confirm('Are you sure you want to delete?')" href="<%=request.getContextPath()%>/products/delete-servlet?id=<%=p.getId()%>">Delete</a></td>
      </tr>
      <%}%>
  </table>


</body>
</html>