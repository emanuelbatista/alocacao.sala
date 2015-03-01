package com.br.ifpb.facade;

import com.br.ifpb.business.object.GerenciarEvento;
import com.br.ifpb.business.object.GerenciarSala;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.util.List;

/**
 * Classe que representa um fachada para Alocar um Evento
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 01/03/2015 às 19:59:01
 */
public class AlocarFachada {

    /**
     * Aloca os 
     * @param eventos
     * @param id
     * @throws PersistenciaException 
     */
    public void alocar(List<Evento> eventos, Integer id) throws PersistenciaException {
        if (eventos != null) {
            GerenciarSala gerenciarSala = new GerenciarSala();
            Sala sala = gerenciarSala.getSala(id);
            GerenciarEvento evento = new GerenciarEvento();
            if (eventos.get(0).getId() == null) {
                for (int i = 0; i < eventos.size(); i++) {
                    eventos.get(i).setSala(sala);
                    eventos.get(i).setStatus(Evento.STATUS_ALOCADO);
                }
                evento.adicionar(eventos.toArray(new Evento[0]));
            } else {
                evento.alocar(id, eventos.toArray(new Evento[0]));
            }
        }
    }
}
