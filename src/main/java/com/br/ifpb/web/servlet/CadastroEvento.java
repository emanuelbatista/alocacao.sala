package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.facade.GerarEventoFacade;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "CadastroEvento", urlPatterns = {"/cadastro-evento"})
public class CadastroEvento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Timestamp dataInicio = criarData(request.getParameter("dataInicio"));
        Timestamp dataFinal = criarData(request.getParameter("dataFinal"));
        String responsavel = request.getParameter("responsavel");
        Integer repiticoes = Integer.valueOf(request.getParameter("totalRepeticao"));
        Integer totalParticipantes = Integer.valueOf(request.getParameter("totalParticipantes"));
        String submit = request.getParameter("submit");
        
        GerarEventoFacade fachada = new GerarEventoFacade();
        List<Evento> eventos = fachada.listarEvento(nome, descricao, dataInicio, dataFinal, responsavel, totalParticipantes, repiticoes);
        
        GerenciarEvento gerenciarEvento = new GerenciarEvento();
        if (submit.equals("Cadastrar")) {
            try {
                gerenciarEvento.adicionar(eventos.toArray(new Evento[0]));
            } catch (PersistenciaException ex) {
                Logger.getLogger(CadastroEvento.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            List<Sala> salasDisponiveis = null;
            try {
                salasDisponiveis = gerenciarEvento.listarSalasDisponiveisEvento(eventos.toArray(new Evento[0]));

            } catch (PersistenciaException ex) {
                Logger.getLogger(GerarEventoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("eventos", eventos);
            request.setAttribute("salasDisponiveis", salasDisponiveis);
            getServletContext().getRequestDispatcher("/alocar-sala.jsp").forward(request, response);
        }
    }

    private Timestamp criarData(String data) throws IllegalArgumentException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, format);
        return Timestamp.valueOf(localDate);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
