package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarSala;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Sala;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "CadastroSala", urlPatterns = {"/cadastro-sala"})
public class CadastroSala extends HttpServlet {

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
        try {
            String tipo = request.getParameter("tipo");
            Integer capacidade = Integer.valueOf(request.getParameter("capacidade"));
            String identificacao = request.getParameter("identificacao");
            String apelido = request.getParameter("apelido");

            GerenciarSala gerenciarSala = new GerenciarSala();
            if (!gerenciarSala.existeIdentificacao(identificacao)) {
                gerenciarSala.adicionar(identificacao, apelido, tipo, capacidade);
                response.sendRedirect("salas");
            }else{
                String mensagemErro="Identificacao já Existe";
                request.setAttribute("mensagemErro", mensagemErro);
                getServletContext().getRequestDispatcher("/cadastro-sala.jsp").forward(request, response);
            }
        } catch (NumberFormatException | PersistenciaException ex) {
            ex.printStackTrace();
        }
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
