package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "BuscarEvento", urlPatterns = {"/buscar-evento"})
public class BuscarEvento extends HttpServlet {

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
        response.sendRedirect("buscar-eventos.jsp");
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
        List<String> mensagensErros = new LinkedList<>();
        try {
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String responsavel = request.getParameter("responsavel");
            String[] status = request.getParameterValues("status");
            Timestamp data = converteTimestamp(request.getParameter("data"));
            GerenciarEvento gerenciarEvento = new GerenciarEvento();
            List<Evento> eventos = gerenciarEvento.buscarEvento(nome, descricao, data, responsavel, status);
            request.setAttribute("eventosPesquisados", eventos);
        } catch (PersistenciaException ex) {
            Logger.getLogger(BuscarEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DateTimeParseException ex) {
            mensagensErros.add("Data está no Formato Errado");
            request.setAttribute("mensagensErros", mensagensErros);
        }
        getServletContext().getRequestDispatcher("/buscar-eventos.jsp").forward(request, response);
    }

    private Timestamp converteTimestamp(String data) throws DateTimeParseException {
        if (data != null && !data.isEmpty()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDate = LocalDateTime.parse(data, format);
            return Timestamp.valueOf(localDate);
        } else {
            return null;
        }
    }

}
