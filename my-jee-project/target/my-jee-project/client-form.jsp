<%@page contentType="UTF-8" import="java.util.*, java.time.format.*, my.jee.project.models.entity.*"%>

<%
Map<String,String>errors = (Map<String, String>) request.getAttribute("errors");
Client client = (Client)request.getAttribute("client");
String registryDateFormated = client.getRegistry() != null ? client.getRegistry().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
//String messageRequest = (String) request.getAttribute("message");
//String messageApplication = (String) getServletContext().getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body style="background-color:#CACFD2;">
<div style="margin-left: 100px; margin-right: 100px">

  <br><br>

  <h2 style="color:#2471A3;">Client form</h2>

    <br>

    <h5><a href="<%=request.getContextPath()%>/clients/list-servlet" class="btn btn-secondary my-2">Return</a></h5>

    <form action="<%=request.getContextPath()%>/clients/form-servlet" method="post">

        <div>
            <label for="name">Name</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="name" id="name" value="<%=client.getName() != null ? client.getName() : "" %>">
            </div>

            <% if(errors != null && errors.containsKey("name")) { %>
            <div style="color:red;"><%= errors.get("name") %></div>
            <%}%>
        </div>

        <div>
            <label for="lastname1">First lastname</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="lastname1" id="lastname1" value="<%=client.getLastName1() != null ? client.getLastName1() : "" %>">
            </div>

            <% if(errors != null && errors.containsKey("lastname1")) { %>
            <div style="color:red;"><%= errors.get("lastname1") %></div>
            <%}%>
        </div>

        <div>
            <label for="lastname2">Second lastname</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="lastname2" id="lastname2" value="<%=client.getLastName2() != null ? client.getLastName2() : "" %>">
            </div>

            <% if(errors != null && errors.containsKey("lastname2")) { %>
            <div style="color:red;"><%= errors.get("lastname2") %></div>
            <%}%>
        </div>

        <div>
            <label for="email">Email</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="email" id="email" value="<%=client.getEmail() != null ? client.getEmail() : "" %>">
            </div>

            <% if(errors != null && errors.containsKey("email")) { %>
            <div style="color:red;"><%= errors.get("email") %></div>
            <%}%>
        </div>

        <div>
            <label for="registry">Date of registry</label>
            <div style="width:25%">
                <input class="form-control" type="date" name="registry" id="registry" value="<%=registryDateFormated%>">
            </div>

            <% if(errors != null && errors.containsKey("registry")) { %>
            <div style="color:red;"><%= errors.get("registry") %></div>
            <%}%>
        </div>

        <br>

        <div style="width:15%">
            <input class="form-control p-2 mb-1 bg-primary text-light" type="submit" value="<%= (client.getId() != null && client.getId() > 0) ? "Update" : "Create" %>">
        </div>
        <input type="hidden" name="id" value="<%= client.getId() %>" >
    </form>



</div>
</body>
</html>