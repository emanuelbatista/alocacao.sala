package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.facade.GerarEventoFacade;
import com.br.ifpb.value.object.Evento;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por preparar o Evento para a sua Alocação
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "EventoAlocacao", urlPatterns = {"/evento-alocacao"})
public class EventoAlocacao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            Integer idEvento = Integer.valueOf(request.getParameter("id"));
            List<Evento> eventos = new LinkedList<>();
            GerenciarEvento gerenciarEvento = new GerenciarEvento();
            Evento evento = gerenciarEvento.getEvento(idEvento);
            if (evento != null) {
                evento.setStatus(Evento.STATUS_ALOCADO);
                eventos.add(evento);
            }
            request.getSession().setAttribute("eventosTemporario", eventos);
            getServletContext().getRequestDispatcher("/salas-disponiveis").forward(request, response);

        } catch (PersistenciaException ex) {
            Logger.getLogger(GerarEventoFacade.class.getName()).log(Level.SEVERE, null, ex);
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
