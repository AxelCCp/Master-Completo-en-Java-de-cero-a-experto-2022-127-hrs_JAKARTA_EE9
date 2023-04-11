<%@page contentType="UTF-8" import="java.util.*, jee.master.models.entity.*"%>

<%
        List<User>userList = (List<User>)request.getAttribute("userList");
        Optional<String>username = (Optional<String>)request.getAttribute("username");
        String messageRequest = (String) request.getAttribute("message");
        String messageApplication = (String) getServletContext().getAttribute("message");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
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

                 <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/users/list-servlet">List of users</a>
                 </li>

              </ul>

              <%if(!username.isPresent()){%>
              <span class="navbar-text">
                      <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/users/login-servlet">Login</a>
               </span>
               <%}%>

               <%if(username.isPresent()){%>
               <span class="navbar-text">
                     <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/users/logout-servlet">Logout</a>
               </span>
               <%}%>

            </div>
          </div>
        </nav>

     <%if(username.isPresent()){%>
      <br><br>
      <div style="margin-left: 20px;">
      <h5><a href="#" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Create a new user</a></h5>
      </div>
      <%}%>
      <br><br>

    <%if(username.isPresent()){%>
    <div style="margin-left: 100px; margin-right: 100px">
    <%} else {%>
    <div style="margin-left: 600px; margin-right: 600px">
    <%}%>

        <h3 style="color:#D7DBDD;">List of users</h3>

        <table class="table table-dark caption-top">

            <thead>
            <tr class="azul">
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
                <%if(username.isPresent()){%>
                <th>Update</th>
                <th>Delete</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
        <% for(User u : userList){%>
            <tr class="morado">
                <td><%=u.getId()%></td>
                <td><%=u.getUsername()%></td>
                <td><%=u.getEmail()%></td>
                <%if(username.isPresent()){%>
                <td><a href="#" class="link-info link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">update</a></td>
                <td><a href="#" class="link-danger link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">delete</a></td>
                <%}%>
            </tr>
        <%}%>
            </tbody>
        </table>
    </div>

    <footer class = "footer">
        <p style="color:#F7F9F9;"><%= messageApplication %> <%= messageRequest %></p>
    </footer>

</body>
</html>