<%-- 
    Document   : Login
    Created on : 17/09/2019, 08:30:35 AM
    Author     : Proyectos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/login.css" />
    </head>
    
    <body>
    <div class="login-page">
    <div class="form">
    <form name="form" action="LoginServlet" method="post" onsubmit="return validarLogin()">

    Usuario: 
    <input type="text" name="usuario" />
    Contraseña: 
    <input type="password" name="password" />

    <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" 
            : request.getAttribute("errMessage")%></span>

     <input type="submit" value="Login"></input>
    </form>
    <form name="form" action="RegistroServlet" method="get">
     <input type="submit" value="Registro" ></input>              
    </form>
    </div>
    </div>
    <script src="${pageContext.request.contextPath}/Resources/js/validaciones.js" type="text/javascript"></script>
    </body>
    
</html>
