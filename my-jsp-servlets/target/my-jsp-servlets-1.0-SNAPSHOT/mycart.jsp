<%@page contentType="text/html" pageEncoding="UTF-8" import="jee.master.models.entity.*" %>

<%
ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your cart</title>
</head>
<body>
<h1>Your cart</h1>

<% if(shoppingCart == null || shoppingCart.getItemsList().isEmpty()){ %>

<p>Sorry, there are no products in the cart.</p>

<% }else { %>

<form name="cartForm" action="<%=request.getContextPath()%>/products/update-cart-servlet" method="post">
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
            <th>Quantity</th>
            <th>Total</th>
            <th>Delete</th>
        </tr>

        <% for(Item myItem : shoppingCart.getItemsList()){ %>

        <tr>
            <td><%= myItem.getProduct().getId() %></td>
            <td><%= myItem.getProduct().getSku() %></td>
            <td><%= myItem.getProduct().getName() %></td>
            <td><%= myItem.getProduct().getComments() %></td>
            <td><%= myItem.getProduct().getDivision().getName() %></td>
            <td><%= myItem.getProduct().getReleaseDate() %></td>
            <td><%= myItem.getProduct().getPuchaseDate() %></td>
            <td><%= myItem.getProduct().getPrice() %></td>
            <td><%= myItem.getQuantity() %></td>
            <td><input type="text" size="4" name="q_<%=myItem.getProduct().getId()%>" value="<%=myItem.getQuantity()%>" /></td>
            <%--<td><%= myItem.getProduct().getPrice() * myItem.getQuantity() %></td>--%>
            <td><input type="checkbox" value="<%=myItem.getProduct().getId()%>" name="deleteProducts" /></td>
        </tr>

        <% } %>

        <tr>
            <td colspan="4" style="text-align: right">Total</td>
            <td><%= shoppingCart.getTotal() %></td>
        </tr>
    </table>

</form>
<% } %>

<a href="javascript:document.cartForm.submit();">Update</a>

    <div>
        <input type="submit" value="Update">
    </div>

<p><a href="<%=request.getContextPath()%>/products/list-servlet">Go to the products list</a></p>

<p><a href="<%= request.getContextPath()%>/index.html">Return home</a></p>
</body>
</html>