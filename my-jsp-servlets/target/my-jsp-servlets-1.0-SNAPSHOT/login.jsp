<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

    <p><a href="<%=request.getContextPath()%>/index.html">Go to home without login</a></p>

  <h1>Login</h1>

  <form action="/my-jsp-servlets/users/login-servlet" method="post">

    <div>
      <label for="username">username</label>
      <div>
        <input type="text" name="username" id="username">
      </div>
    </div>

    <div>
      <label for="password">password</label>
      <div>
        <input type="text" name="password" id="password">
      </div>
    </div>

    <div>
      <input type="submit" value="Login">
    </div>

  </form>
</body>
</html>