
package com.br.ifpb.web.servlet;

import com.br.ifpb.business.object.GerenciarEvento;
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
 *Servlet responsável por controlar desalocamento dos Eventos  
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name="Desalocar", urlPatterns={"/desalocar"})
public class Desalocar extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
        try {
            Integer idEvento=Integer.valueOf(request.getParameter("id"));
            GerenciarEvento gerenciarEvento=new GerenciarEvento();
            gerenciarEvento.desalocar(idEvento);
            response.sendRedirect("eventos");
        } catch (PersistenciaException ex) {
            Logger.getLogger(Desalocar.class.getName()).log(Level.SEVERE, null, ex);
        }

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
