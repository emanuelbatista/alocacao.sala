
package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.business.object.GerenciarSala;
import com.br.ifpb.execoes.PersistenciaException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por remover Salas
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name="RemoverSala", urlPatterns={"/remover-sala"})
public class RemoverSala extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Integer id=Integer.valueOf(request.getParameter("id"));
            GerenciarEvento gerenciarEvento=new GerenciarEvento();
            gerenciarEvento.desalocarPelaSala(id);
            GerenciarSala gerenciarSala=new GerenciarSala();
            gerenciarSala.remover(id);
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(RemoverSala.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("salas");
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
