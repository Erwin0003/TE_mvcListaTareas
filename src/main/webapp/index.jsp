<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Ltarea"%>
<%
    List<Ltarea> lista = (List<Ltarea>) session.getAttribute("listaper");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de tareas</h1>
        <p> <a href="MainController?op=nuevo">Nuevo </a></p>
        <table border="1" cellspacing="0" RULES="cols">
            <tr style="background-color:#DCDCDC">
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <%! String nivel1 = "alto";%>
            <%! String nivel2 = "medio";%>
            <%! String nivel3 = "bajo";%>

            <%
                if (lista != null) {
                    for (Ltarea ltarea : lista) {

            %>
            <td><%= ltarea.getId()%></td>
            <td><%= ltarea.getTarea()%></td>
            <%  if (ltarea.getPrioridad() == 1) {%>
            <td><%= nivel1%> </td>
            <%   }%>
            <%  if (ltarea.getPrioridad() == 2) {%>
            <td><%= nivel2%> </td>
            <%   }%>
            <%  if (ltarea.getPrioridad() == 3) {%>
            <td><%= nivel3%> </td>
            <%   }%>
           

            <%  if (ltarea.getCompletado() == 1) {%>
            <td><input type="checkbox" checked="checked"></td>
                <%   }%>
                <%  if (ltarea.getCompletado() == 2) {%>
            <td> <input type="checkbox" > </td>
                <%   }%>

            <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
            <td><a href="MainController?op=modificar&id=${item.id}">Modificar</a></td>
        </tr>

        <%
                }
            }
        %>
</body>
</html>
