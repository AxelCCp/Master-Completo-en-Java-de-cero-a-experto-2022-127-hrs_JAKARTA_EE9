<%@page contentType="UTF-8" import="java.util.*, java.time.format.*, jee.master.models.entity.*"%>

<%
    List<Division>divisionsList = (List<Division>)request.getAttribute("divisionsList");
    Product product = (Product)request.getAttribute("product");
    String releaseDateFormated = product.getReleaseDate() != null ? product.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    String puchaseDateFormated = product.getPuchaseDate() != null ? product.getPuchaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    Map<String,String>errors = (Map<String, String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form</title>
</head>
<body>

<h1>Formulario productos</h1>

<form action="<%=request.getContextPath()%>/products/form-servlet" method="post">

    <div>
        <label for="sku">Sku</label>
        <div>
            <input type="text" name="sku" id="sku" value="<%=product.getSku() != null ? product.getSku() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("sku")) { %>
        <div style="color:red;"><%= errors.get("sku") %></div>
        <%}%>
    </div>

    <div>
        <label for="name">Name</label>
        <div>
            <input type="text" name="name" id="name" value="<%=product.getName() != null ? product.getName() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("name")) { %>
        <div style="color:red;"><%= errors.get("name") %></div>
        <%}%>
    </div>

    <div>
        <label for="comments">Comments</label>
        <div>
            <input type="text" name="comments" id="comments" value="<%=product.getComments() != null ? product.getComments() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("comments")) { %>
        <div style="color:red;"><%= errors.get("comments") %></div>
        <%}%>
    </div>

    <div>
        <label for="division">Division</label>
        <div>
            <select name="division" id="division">
                <option value="">--- seleccionar ---</option>
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
        <div>
            <input type="date" name="releaseDate" id="releaseDate" value="<%=releaseDateFormated%>">
        </div>
        <% if(errors != null && errors.containsKey("releaseDate")) { %>
        <div style="color:red;"><%= errors.get("releaseDate") %></div>
        <%}%>
    </div>

    <div>
        <label for="puchaseDate">Purchase date</label>
        <div>
            <input type="date" name="puchaseDate" id="puchaseDate" value="<%=puchaseDateFormated%>">
        </div>
        <% if(errors != null && errors.containsKey("puchaseDate")) { %>
        <div style="color:red;"><%= errors.get("puchaseDate") %></div>
        <%}%>
    </div>

    <div>
        <label for="price">Price</label>
        <div>
            <input type="number" name="price" id="price" value="<%=product.getPrice() != null ? product.getPrice() : "" %>">
        </div>
        <% if(errors != null && errors.containsKey("price")) { %>
        <div style="color:red;"><%= errors.get("price") %></div>
        <%}%>
    </div>

    <div>
        <input type="submit" value="<%= (product.getId() != null && product.getId() > 0) ? "Update" : "Create" %>">
    </div>
    <input type="hidden" name="id" value="<%= product.getId() %>" >
</form>

</body>
</html>