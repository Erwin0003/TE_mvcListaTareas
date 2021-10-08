<%@page import="com.emergentes.modelo.Ltarea"%>
<%
    Ltarea ltarea = (Ltarea) request.getAttribute("miLtarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= (ltarea.getId() == 0) ? "Nuevo Tarea" : "Editar tarea"%></h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="<%=ltarea.getId()%>"/>
            <table>
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="<%=ltarea.getTarea()%>" /> </td>
                </tr>
                <tr>
                    <td>Prioridad</td>
                    <td><input type="number" min="1" max="3" name="prioridad" value="<%=ltarea.getPrioridad()%>"/></td>
                </tr>
                <tr>
                    <td>Completado</td>
                    <td><input type="number" min="1" max="2" name="completado" value="<%=ltarea.getCompletado()%>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
