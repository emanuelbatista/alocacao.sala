package com.br.ifpb.web.servlet;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.facade.AlocarFachada;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por <b>Alocar</b> a {@link Sala} ao {@link Evento}
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "Alocar", urlPatterns = {"/alocar"})
public class Alocar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<Evento> eventos = (List<Evento>) request.getSession().getAttribute("eventosTemporario");
        AlocarFachada alocarFachada=new AlocarFachada();
        try {
            alocarFachada.alocar(eventos, id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Alocar.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().removeAttribute("eventos");
        response.sendRedirect("eventos");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
