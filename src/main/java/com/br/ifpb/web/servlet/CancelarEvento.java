package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Servlet responsável por fazer o controle do <b>Cancelamento de Eventos</b> 
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "CancelarEvento", urlPatterns = {"/cancelar-evento"})
public class CancelarEvento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer idEvento=Integer.valueOf(request.getParameter("id"));
        try {
            GerenciarEvento gerenciarEvento = new GerenciarEvento();
            gerenciarEvento.mudarStatus(idEvento, Evento.STATUS_CANCELADO);
            response.sendRedirect("eventos");
        } catch (PersistenciaException ex) {
            Logger.getLogger(CancelarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("eventos");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
