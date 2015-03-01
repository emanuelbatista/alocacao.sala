
package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarSala;
import com.br.ifpb.execoes.PersistenciaException;
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
 * Servlet responsável por resgatar todas as SalasS
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name="Salas", urlPatterns={"/salas"})
public class Salas extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            GerenciarSala gerenciarSala=new GerenciarSala();
            List<Sala> salas=gerenciarSala.listar();
            request.setAttribute("salas", salas);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Salas.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/salas.jsp").forward(request, response);

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
