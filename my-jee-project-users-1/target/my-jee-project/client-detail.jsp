<%@page contentType="UTF-8" import="java.util.*, java.time.format.*, my.jee.project.models.entity.*"%>

<%
Client client = (Client)request.getAttribute("client");
String registryDateFormated = client.getRegistry() != null ? client.getRegistry().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<body style="background-color:#CACFD2;">

<div style="margin-left: 100px; margin-right: 100px">

  <br><br>

  <h2 style="color:#2471A3;">Client Detail</h2>

    <br>

    <h5><a href="<%=request.getContextPath()%>/clients/list-servlet" class="btn btn-secondary my-2">Return</a></h5>

    <form>

        <div>
            <label for="name">Name</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="name" id="name" value="<%=client.getName() != null ? client.getName() : "" %>" disabled>
            </div>
        </div>

        <div>
            <label for="lastname1">First lastname</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="lastname1" id="lastname1" value="<%=client.getLastName1() != null ? client.getLastName1() : "" %>" disabled>
            </div>
        </div>

        <div>
            <label for="lastname2">Second lastname</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="lastname2" id="lastname2" value="<%=client.getLastName2() != null ? client.getLastName2() : "" %>" disabled>
            </div>
        </div>

        <div>
            <label for="email">Email</label>
            <div style="width:50%">
                <input class="form-control" type="text" name="email" id="email" value="<%=client.getEmail() != null ? client.getEmail() : "" %>" disabled>
            </div>
        </div>

        <div>
            <label for="registry">Date of registry</label>
            <div style="width:25%">
                <input class="form-control" type="date" name="registry" id="registry" value="<%=registryDateFormated%>" disabled>
            </div>
        </div>


    </form>

</div>

</body>
</html>