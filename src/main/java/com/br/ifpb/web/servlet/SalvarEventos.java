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
 * Servlet responsável por pegar os eventos que não foram alocados a Sala
 * e salvar na base de dados
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "SalvarEventos", urlPatterns = {"/salvar-eventos"})
public class SalvarEventos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Evento> eventos = (List<Evento>) request.getSession().getAttribute("eventos");
        if (eventos != null) {
            try {
                if (eventos.get(0).getId() == null) {
                    GerenciarEvento gerenciarEvento = new GerenciarEvento();
                    gerenciarEvento.adicionar(eventos.toArray(new Evento[0]));
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(SalvarEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().removeAttribute("eventos");
        }
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
