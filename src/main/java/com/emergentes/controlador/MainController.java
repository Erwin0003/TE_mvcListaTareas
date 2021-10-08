
package com.emergentes.controlador;

import com.emergentes.modelo.Ltarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ErwinEM
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession ses = request.getSession();
        
        if (ses.getAttribute("listaper") == null){
            ArrayList<Ltarea> listaux = new ArrayList<Ltarea>();
            ses.setAttribute("listaper", listaux);
        }
        
        ArrayList<Ltarea> lista = (ArrayList<Ltarea>)ses.getAttribute("listaper");
        
        
        String op = request.getParameter("op");
        String opcion = (op != null) ? op : "view";
        
        Ltarea obj1 = new Ltarea();
        int id, pos;
        
        switch (opcion){
            case "nuevo":
                request.setAttribute("miLtarea", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                obj1 = lista.get(pos);
                request.setAttribute("miLtarea", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                lista.remove(pos);
                ses.setAttribute("listaper", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession ses =  request.getSession();
        ArrayList<Ltarea> lista = (ArrayList<Ltarea>)ses.getAttribute("listaper");
        
        Ltarea obj1 = new Ltarea();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setTarea(request.getParameter("tarea"));
        obj1.setPrioridad(Integer.parseInt(request.getParameter("prioridad")));
        obj1.setCompletado(Integer.parseInt(request.getParameter("completado")));
        
        int idt = obj1.getId();
        
        if (idt == 0){
            // Nuevo
            // Actualizar el ultimo id
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }
        else{
            // Edicion
            lista.set(buscarIndice(request,idt), obj1);
        }
        ses.setAttribute("listaper", lista);
        response.sendRedirect("index.jsp");                
    }
    
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Ltarea> lista = (ArrayList<Ltarea>)ses.getAttribute("listaper");
        
        int i = 0;
        
        if (lista.size() > 0){
            while (i < lista.size()){
                if (lista.get(i).getId() == id){
                    break;
                }
                else {
                    i++;
                }
            }
        }
        return i;
    }
    
    private int ultimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Ltarea> lista = (ArrayList<Ltarea>)ses.getAttribute("listaper");
        
        int idaux = 0;
        for (Ltarea ltarea: lista){
            idaux = ltarea.getId();
        }
        return idaux + 1;
    }

 
}