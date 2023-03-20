<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%--
List<String>errores = (List<String>)request.getAttribute("errores");
--%>

<% Map<String, String> errores = (Map<String, String>)request.getAttribute("errores"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
</head>
<body>

  <h3>Formulario de usuarios</h3>

<%-- CON LIST

  <% if(errores != null && errores.size() > 0){ %>
    <ul>
        <% for(String error : errores){ %>

            <li><%=error %></li>

        <% } %>
    </ul>
  <% } %>

--%>

  <% if(errores != null && errores.size() > 0){ %>
    <ul>
        <% for(String error : errores.values()){ %>

            <li><%=error %></li>

        <% } %>
    </ul>
  <% } %>



  <form action="/webapp-form/registro" method="post">

    <div>
      <label for="username">Usuario</label>
      <div><input type="text" name="username" id="username" value="${param.username}"></div>
      <%
      if(errores != null && errores.containsKey("username")){
        out.println("<small style='color:red'>" + errores.get("username") + "</small>");
      }
      %>
    </div>

    <div>
      <label for="password">Password</label>
      <div><input type="password" name="password" id="password"></div>
      <%
         if(errores != null && errores.containsKey("password")){
           out.println("<small style='color:red'>" + errores.get("password") + "</small>");
         }
       %>
    </div>

    <div>
      <label for="email">Email</label>
      <div><input type="text" name="email" id="email" value="${param.email}"></div>
        <%
          if(errores != null && errores.containsKey("email")){
            out.println("<small style='color:red'>" + errores.get("email") + "</small>");
          }
        %>
    </div>

    <div>
        <label for="pais"></label>
        <div>
          <select name="pais" id="pais">
            <option value="">-- seleccionar --</option>
            <option value="ES" ${param.pais=="ES" ? "selected" : ""}>España</option>
            <option value="MX" ${param.pais=="MX" ? "selected" : ""}>Mexico</option>
            <option value="CH" ${param.pais=="CH" ? "selected" : ""}>Chile</option>
            <option value="CO" ${param.pais=="CO" ? "selected" : ""}>Colombia</option>
            <option value="AR" ${param.pais=="AR" ? "selected" : ""}>Argentina</option>
          </select>
        </div>
            <%
            if(errores != null && errores.containsKey("pais")){
              out.println("<small style='color:red'>" + errores.get("pais   ") + "</small>");
            }
            %>
    </div>

    <div>
      <label for="lenguajes">Lenguajes de programación</label>
      <div>
        <select name="lenguajes" id="lenguajes" multiple>
          <option value="java" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("java")).get() ? "selected" : ""}>Java</option>
          <option value="jakartaee" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("jakartaee")).get() ? "selected" : ""}>Java EE</option>
          <option value="spring" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("spring")).get() ? "selected" : ""}>Spring boot</option>
          <option value="js" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("js")).get() ? "selected" : ""}>Javascript</option>
          <option value="angular" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("angular")).get() ? "selected" : ""}>Angular</option>
          <option value="react" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("react")).get() ? "selected" : ""}>React</option>
        </select>
      </div>
          <%
          if(errores != null && errores.containsKey("lenguajes")){
            out.println("<small style='color:red'>" + errores.get("lenguajes") + "</small>");
          }
          %>
    </div>

    <div>
      <label>Roles</label>
      <div>
        <input type="checkbox" name="roles" value="ROLE_ADMIN" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_ADMIN")).get() ? "checked" : ""}>
        <label>Administrador</label>
      </div>
      <div>
        <input type="checkbox" name="roles" value="ROLE_USER" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_USER")).get() ? "checked" : ""}>
        <label>Usuario</label>
      </div>
      <div>
        <input type="checkbox" name="roles" value="ROLE_MODERATOR" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_MODERATOR")).get() ? "checked" : ""}>
        <label>Moderador</label>
      </div>
         <%
         if(errores != null && errores.containsKey("roles")){
           out.println("<small style='color:red'>" + errores.get("roles") + "</small>");
         }
         %>
    </div>

    <div>
      <label class="col-form-label col-sm-2">Idiomas</label>
      <div>
        <input type="radio" name="idioma" value="es" ${param.idioma=="es" ? "checked" : ""}>
        <label>Español</label>
      </div>
      <div>
        <input type="radio" name="idioma" value="en" ${param.idioma=="en" ? "checked" : ""}>
        <label>Inglés</label>
      </div>
      <div>
        <input type="radio" name="idioma" value="jp" ${param.idioma=="jp" ? "checked" : ""}>
        <label>Japonés</label>
      </div>
            <%
            if(errores != null && errores.containsKey("idioma")){
              out.println("<small style='color:red'>" + errores.get("idioma") + "</small>");
            }
            %>
    </div>

    <div>
      <label for="habilitar">Habilitar</label>
      <div class="form-check">
        <input type="checkbox" name="habilitar" id="habilitar" checked>
      </div>
    </div>

    <div>
      <div>
        <input type="submit" value="Enviar">
      </div>
    </div>

    <input type="hidden" value="12345" name="secreto">

  </form>
</body>
</html>