package com.br.ifpb.business.object;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.EventoDaoIf;
import com.br.ifpb.persiste.dao.factory.DaoFactory;
import com.br.ifpb.persiste.dao.factory.DaoFactoryIf;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 06/02/2015 ás 09:32:55
 */
public class GerenciarEvento {

    private EventoDaoIf getEventoDao() throws PersistenciaException {
        DaoFactoryIf daoFactory = DaoFactory.createFactory();
        return daoFactory.createEventoDao();
    }

    public void adicionar(String nome, String descricao, Timestamp dataInicio,
            Timestamp dataFinal, Integer totolParticipantes, String responsavel, String status, Sala sala)
            throws PersistenciaException {
        Evento evento = new Evento();
        evento.setDataFinal(dataFinal);
        evento.setDataInicio(dataInicio);
        evento.setDescricao(descricao);
        evento.setNome(nome);
        evento.setResponsavel(responsavel);
        evento.setSala(sala);
        evento.setStatus(status);
        evento.setTotalParticipantes(totolParticipantes);
        getEventoDao().adicionar(evento);
    }

    public void adicionar(Evento... eventos)
            throws PersistenciaException {
        getEventoDao().adicionar(eventos);
    }

    public List<Sala> listarSalasDisponiveisEvento(Evento... eventos) throws PersistenciaException {
        return getEventoDao().listarSalasDisponiveisEvento(eventos);
    }
    
    public void alocar(Integer id_sala,Evento... evento) throws PersistenciaException{
        getEventoDao().alocar(id_sala, evento);
    }
    
    public List<Evento> buscarEvento(String nome, String descricao,Timestamp data,
            String responsavel,String status) throws PersistenciaException{
        return getEventoDao().buscarEvento(nome, descricao, data, responsavel, status);
    }
}
