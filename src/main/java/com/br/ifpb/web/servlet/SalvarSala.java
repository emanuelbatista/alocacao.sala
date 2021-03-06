package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarSala;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Sala;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SalvarSala", urlPatterns = {"/salvar-sala"})
public class SalvarSala extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");
            Integer id = Integer.valueOf(request.getParameter("id"));
            String tipo = request.getParameter("tipo");
            Integer capacidade = Integer.valueOf(request.getParameter("capacidade"));
            String identificacao = request.getParameter("identificacao");
            String apelido = request.getParameter("apelido");
            GerenciarSala gerenciarSala = new GerenciarSala();
            Sala sala=gerenciarSala.getSala(id);
            if (!gerenciarSala.existeIdentificacao(identificacao) || sala.getIdentificacao().equals(identificacao)) {
                gerenciarSala.atualizar(id, identificacao, apelido, tipo, capacidade);
                response.sendRedirect("salas");
            }else{
                String mensagemErro="Identificacao já Existe";
                request.setAttribute("mensagemErro", mensagemErro);
                getServletContext().getRequestDispatcher("/editar-sala").forward(request, response);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(SalvarSala.class.getName()).log(Level.SEVERE, null, ex);
        }

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
