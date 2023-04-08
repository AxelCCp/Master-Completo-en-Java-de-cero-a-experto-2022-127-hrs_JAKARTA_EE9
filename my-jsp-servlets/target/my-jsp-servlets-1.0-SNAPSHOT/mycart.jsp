<%@page contentType="text/html" pageEncoding="UTF-8" import="jee.master.models.entity.*" %>

<%
ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
%>
<%
String messageRequest = (String) request.getAttribute("message");
String messageApplication = (String) getServletContext().getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Your cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<style type="text/css">
footer {
  background-color: #2E4053;
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 30px;
}
.morado {
    color: #C39BD3; }

.azul {
    color: #5DADE2}
</style>

<body style="background-color:#95A5A6;">

<nav class="navbar bg-dark navbar-expand-lg" data-bs-theme="dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/index.html">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">

          <ul class="navbar-nav me-auto mb-2 mb-lg-0">

            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/products/list-servlet">List of products</a>
            </li>

             <li class="nav-item">
               <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/products/cart-review-servlet">Cart review</a>
             </li>

          </ul>

            <span class="navbar-text">
                <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/users/logout-servlet">Logout</a>
            </span>

        </div>
      </div>
    </nav>


<br><br>

<div style="margin-left: 100px; margin-right: 100px">

<h3 style="color:#D7DBDD;">Your cart</h3>

<% if(shoppingCart == null || shoppingCart.getItemsList().isEmpty()){ %>

<p>Sorry, there are no products in the cart.</p>

<% }else { %>

<form name="cartForm" action="<%=request.getContextPath()%>/products/update-cart-servlet" method="post">
    <table class="table table-dark caption-top">
        <tr class="azul">
            <th>Id</th>
            <th>Sku</th>
            <th>Name</th>
            <th>Comments</th>
            <th>Division</th>
            <th>Release date</th>
            <th>Purchase date</th>
            <th>Price</th>
            <th>Quantity</th>
            <th style="color:#EB984E;">Total</th>
            <th style="color:#E74C3C;">Delete</th>
        </tr>

        <% for(Item myItem : shoppingCart.getItemsList()){ %>

        <tr class="morado">
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
            <td colspan="9" style="text-align: right"><h5 style="color:#F7DC6F;">Total:</h5></td>
            <td><h4 style="color:#73C6B6;"><%= shoppingCart.getTotal() %></h4></td>
        </tr>
    </table>

</form>
<% } %>



<h5><a class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" href="javascript:document.cartForm.submit();">Update</h5></p>


</div>

<footer class = "footer">
  <p style="color:#F7F9F9;"><%= messageApplication %> <%= messageRequest %></p>
</footer>

</body>
</html>