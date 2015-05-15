package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
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
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "LevantamentoEventos", urlPatterns = {"/levantamento-eventos"})
public class LevantamentoEventos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String status = request.getParameter("status");
        String confirmacao=request.getParameter("confirmacao");
        Integer idEvento = Integer.valueOf(request.getParameter("id"));
        if (status.equals(Evento.STATUS_PENDENTE_LOCAL) && confirmacao.equals(Evento.STATUS_REALIZADO)) {
            try {
                List<Evento> eventos = new LinkedList<>();
                GerenciarEvento gerenciarEvento = new GerenciarEvento();
                Evento evento = gerenciarEvento.getEvento(idEvento);
                if (evento != null) {
                    evento.setStatus(Evento.STATUS_REALIZADO);
                    eventos.add(evento);
                }
                request.getSession().setAttribute("eventos", eventos);
                List<Sala> salasDisponiveis = gerenciarEvento.listarSalasDisponiveisEvento(eventos.toArray(new Evento[0]));
                request.setAttribute("salasDisponiveis", salasDisponiveis);
                getServletContext().getRequestDispatcher("/alocar-sala.jsp").forward(request, response);

            } catch (PersistenciaException ex) {
                Logger.getLogger(LevantamentoEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                GerenciarEvento gerenciarEvento=new GerenciarEvento();
                gerenciarEvento.mudarStatus(idEvento, confirmacao);
                response.sendRedirect("eventos");
            } catch (PersistenciaException ex) {
                Logger.getLogger(LevantamentoEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
