<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String messageRequest = (String) request.getAttribute("message");
String messageApplication = (String) getServletContext().getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
    .morado {color: #C39BD3; }
    .azul {color: #5DADE2}
</style>

<body style="background-color:#95A5A6;">

    <div>
        <nav class="navbar bg-dark" data-bs-theme="dark">
             <div class="container-fluid">
                   <a class="navbar-brand" href="<%=request.getContextPath()%>/index.html">Home</a>
             </div>
        </nav>
    </div>

    <br><br><br><br>

  <div style="margin-left: 300px; margin-right: 300px">

  <h3 style="color:#D7DBDD;">Login</h3>

  <form action="/my-jsp-servlets/users/login-servlet" method="post">

    <div>
      <label for="username">Username</label>
      <div style="width:50%">
        <input class="form-control" type="text" name="username" id="username">
      </div>
    </div>

    <div>
      <label for="password">Password</label>
      <div style="width:50%">
        <input class="form-control" type="password" name="password" id="password">
      </div>
    </div>

    <br>

    <div style="width:15%">
      <input class="form-control p-2 mb-1 bg-primary text-light" type="submit" value="Login">
    </div>

  </form>

  </div>

<footer class = "footer">
  <p style="color:#F7F9F9;"><%= messageApplication %> <%= messageRequest %></p>
</footer>
</body>
</html>