package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
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
 * Servlet responsável por gerar os Eventos de acordo com seu status
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "EventosStatus", urlPatterns = {"/eventos-status"})
public class EventosStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String status = request.getParameter("status");
            GerenciarEvento gerenciarEvento = new GerenciarEvento();
            List<Evento> eventos=gerenciarEvento.listarEventoStatus(status);
            request.setAttribute("eventos", eventos);
            getServletContext().getRequestDispatcher("/eventos.jsp").forward(request, response);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EventosStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        

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
