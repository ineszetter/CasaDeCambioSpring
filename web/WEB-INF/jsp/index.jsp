<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EJEMPLO DE ACCESO A DATOS</title>
    </head>

    <body>
       
        <table>
            
            
        <c:forEach var="registro" items="${lista}">
            <tr> 
                <td>${registro.nombre}</td>
                <td>${registro.valor}</td>
            </tr>            
        </c:forEach>
        </table>
    </body>
</html>
