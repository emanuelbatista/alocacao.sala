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
 *Recebe a requisição para cadastro de uma  {@link Sala}}
 * e efetua o cadastro de sala de acordo com a requisição
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "CadastroSala", urlPatterns = {"/cadastro-sala"})
public class CadastroSala extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


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


}
