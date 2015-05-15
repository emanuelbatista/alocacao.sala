package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
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
 * Servlet responsável por gerar as salas disponíveis para alocar os Eventos
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "SalasDisponiveis", urlPatterns = {"/salas-disponiveis"})
public class SalasDisponiveis extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            List<Evento> eventos = (List<Evento>) request.getSession().getAttribute("eventosTemporario");
            GerenciarEvento gerenciarEvento = new GerenciarEvento();
            List<Sala> salasDisponiveis = gerenciarEvento.listarSalasDisponiveisEvento(eventos.toArray(new Evento[0]));
            request.setAttribute("salasDisponiveis", salasDisponiveis);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SalasDisponiveis.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/alocar-sala.jsp").forward(request, response);
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
