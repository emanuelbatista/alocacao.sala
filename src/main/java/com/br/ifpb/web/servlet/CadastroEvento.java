package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.facade.GerarEventoFacade;
import com.br.ifpb.converter.ConverterInformacao;
import com.br.ifpb.value.object.Evento;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
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
 *Servlet responsável por fazer o controle do <b>Cadastro do Evento</b>
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "CadastroEvento", urlPatterns = {"/cadastro-evento"})
public class CadastroEvento extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<String> mensagensErros = new LinkedList<>();
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String responsavel = request.getParameter("responsavel");
        Integer repiticoes = Integer.valueOf(request.getParameter("totalRepeticao"));
        Integer totalParticipantes = Integer.valueOf(request.getParameter("totalParticipantes"));
        String submit = request.getParameter("submit");
        Timestamp dataInicio = null;
        Timestamp dataFinal = null;
        try {
            dataInicio = ConverterInformacao.converteTimestamp(request.getParameter("dataInicio"));
            dataFinal = ConverterInformacao.converteTimestamp(request.getParameter("dataFinal"));
            if(dataInicio.compareTo(dataFinal)>0){
               throw new Exception();
            }
        } catch (DateTimeParseException ex) {
            mensagensErros.add("Data Final Formato Invalido");
        } catch (Exception ex) {
            mensagensErros.add("Data inicial está maior que a Final");
        }
        if (mensagensErros.isEmpty()) {
            try {
                GerarEventoFacade fachada = new GerarEventoFacade();
                List<Evento> eventos = fachada.listarEvento(nome, descricao, dataInicio, dataFinal, 
                        responsavel, totalParticipantes, repiticoes);
                GerenciarEvento gerenciarEvento = new GerenciarEvento();
                if (submit.equals("Cadastrar")) {
                    gerenciarEvento.adicionar(eventos.toArray(new Evento[0]));
                    response.sendRedirect("eventos");
                } else {
                    request.getSession().setAttribute("eventosTemporario", eventos);
                    getServletContext().getRequestDispatcher("/salas-disponiveis").forward(request, response);
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerarEventoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           request.setAttribute("mensagensErros", mensagensErros);
           getServletContext().getRequestDispatcher("/cadastro-evento.jsp").forward(request, response);
        }
    }


}
