<%@page contentType="UTF-8" import="java.util.*, java.time.format.*, jee.master.models.entity.*"%>

<%
    List<Division>divisionsList = (List<Division>)request.getAttribute("divisionsList");
    Product product = (Product)request.getAttribute("product");
    String releaseDateFormated = product.getReleaseDate() != null ? product.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    String puchaseDateFormated = product.getPuchaseDate() != null ? product.getPuchaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    Map<String,String>errors = (Map<String, String>) request.getAttribute("errors");
%>
<%
String messageRequest = (String) request.getAttribute("message");
String messageApplication = (String) getServletContext().getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products Form</title>
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
</style>

<body style="background-color:#95A5A6;">

<div style="margin-left: 300px; margin-right: 300px">

<br><br>

<h3 style="color:#D7DBDD;">Products form</h1>

<br>

<h5><a href="<%=request.getContextPath()%>/products/list-servlet" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Return</a></h5>

<br>

<form action="<%=request.getContextPath()%>/products/form-servlet" method="post">

    <div>
        <label for="sku">Sku</label>
        <div style="width:50%">
            <input class="form-control" type="text" name="sku" id="sku" value="<%=product.getSku() != null ? product.getSku() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("sku")) { %>
        <div style="color:red;"><%= errors.get("sku") %></div>
        <%}%>
    </div>

    <div>
        <label for="name">Name</label>
        <div style="width:50%">
            <input class="form-control" type="text" name="name" id="name" value="<%=product.getName() != null ? product.getName() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("name")) { %>
        <div style="color:red;"><%= errors.get("name") %></div>
        <%}%>
    </div>

    <div>
        <label for="comments">Comments</label>
        <div style="width:50%">
            <input class="form-control" type="text" name="comments" id="comments" value="<%=product.getComments() != null ? product.getComments() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("comments")) { %>
        <div style="color:red;"><%= errors.get("comments") %></div>
        <%}%>
    </div>

    <div>
        <label for="division">Division</label>
        <div style="width:25%">
            <select class="form-control" name="division" id="division">
                <option value=""> select </option>
                <%for(Division d : divisionsList){%>
                <option value="<%=d.getId()%>" <%= d.getId().equals(product.getDivision().getId()) ? "selected" : ""%>> <%=d.getName()%> </option>
                <%}%>
            </select>
        </div>
        <% if(errors != null && errors.containsKey("division")) { %>
        <div style="color:red;"><%= errors.get("division") %></div>
        <%}%>
    </div>

    <div>
        <label for="releaseDate">Release date</label>
        <div style="width:25%">
            <input class="form-control" type="date" name="releaseDate" id="releaseDate" value="<%=releaseDateFormated%>">
        </div>
        <% if(errors != null && errors.containsKey("releaseDate")) { %>
        <div style="color:red;"><%= errors.get("releaseDate") %></div>
        <%}%>
    </div>

    <div>
        <label for="puchaseDate">Purchase date</label>
        <div style="width:25%">
            <input class="form-control" type="date" name="puchaseDate" id="puchaseDate" value="<%=puchaseDateFormated%>">
        </div>
        <% if(errors != null && errors.containsKey("puchaseDate")) { %>
        <div style="color:red;"><%= errors.get("puchaseDate") %></div>
        <%}%>
    </div>

    <div>
        <label for="price">Price</label>
        <div style="width:50%">
            <input class="form-control" type="number" name="price" id="price" value="<%=product.getPrice() != null ? product.getPrice() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("price")) { %>
        <div style="color:red;"><%= errors.get("price") %></div>
        <%}%>
    </div>

    <br>

    <div style="width:15%">
        <input class="form-control p-2 mb-1 bg-primary text-light" type="submit" value="<%= (product.getId() != null && product.getId() > 0) ? "Update" : "Create" %>">
    </div>
    <input type="hidden" name="id" value="<%= product.getId() %>" >
</form>

</div>

<footer class = "footer">
  <p style="color:#F7F9F9;"><%= messageApplication %> <%= messageRequest %></p>
</footer>

</body>
</html>