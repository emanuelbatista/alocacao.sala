package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.business.object.GerenciarSala;
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
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "Alocar", urlPatterns = {"/alocar"})
public class Alocar extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        Integer id = Integer.valueOf(request.getParameter("id"));

        List<Evento> eventos = (List<Evento>) request.getSession().getAttribute("eventosTemporario");
        if (eventos != null) {
            try {
                GerenciarSala gerenciarSala = new GerenciarSala();

                Sala sala = gerenciarSala.getSala(id);

                GerenciarEvento evento = new GerenciarEvento();
                if (eventos.get(0).getId() == null) {
                    for (int i=0;i<eventos.size();i++) { 
                        eventos.get(i).setSala(sala);
                        eventos.get(i).setStatus(Evento.STATUS_ALOCADO);
                    }
                    evento.adicionar(eventos.toArray(new Evento[0]));
                } else {
                    evento.alocar(id, eventos.toArray(new Evento[0]));
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(Alocar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getSession().removeAttribute("eventos");
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
