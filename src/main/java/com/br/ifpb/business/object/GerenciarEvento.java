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
 * Responsável por gerenciar toda a regra de negocio sobre o {@link Evento}
 * ele repassa as informações do Evento para base de dados
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 06/02/2015 às 09:32:55
 * @version 1.0
 */
public class GerenciarEvento {

    /**
     * Ele retorna a classe que persiste {@link Evento} 
     * @return {@link EventoDaoIf}
     * @throws PersistenciaException 
     */
    private EventoDaoIf getEventoDao() throws PersistenciaException {
        DaoFactoryIf daoFactory = DaoFactory.createFactory();
        return daoFactory.createEventoDao();
    }
    /**
     * Salva as informações referente a {@link Evento} 
     * @param nome
     * @param descricao
     * @param dataInicio
     * @param dataFinal
     * @param totolParticipantes
     * @param responsavel
     * @param status
     * @param sala
     * @throws PersistenciaException 
     */
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
   /**
    * Salva as informações referente a {@link Evento} 
    * @param eventos
    * @throws PersistenciaException 
    */
    public void adicionar(Evento... eventos)
            throws PersistenciaException {
        getEventoDao().adicionar(eventos);
    }

    /**
     * Retorna as sala disponiveis de acordo com os <b>Eventos</b> 
     * @param eventos
     * @return {@link List}
     * @throws PersistenciaException 
     */
    public List<Sala> listarSalasDisponiveisEvento(Evento... eventos) throws PersistenciaException {
        return getEventoDao().listarSalasDisponiveisEvento(eventos);
    }
    
    /**
     * Repassa os parâmetros necessários para alocar um Evento
     * @param id_sala
     * @param evento
     * @throws PersistenciaException 
     */
    public void alocar(Integer id_sala, Evento... evento) throws PersistenciaException {
        getEventoDao().alocar(id_sala, evento);
    }
    /**
     * Retorna os Eventos de acordo com os parâmetros necessários para buscar Eventos 
     * @param nome
     * @param descricao
     * @param data
     * @param responsavel
     * @param status
     * @return {@link List}
     * @throws PersistenciaException 
     */
    public List<Evento> buscarEvento(String nome, String descricao, Timestamp data,
            String responsavel, String... status) throws PersistenciaException {
        return getEventoDao().buscarEvento(nome, descricao, data, responsavel, status);
    }
    
    /**
     * Retorna todos Eventos cadastrado
     * @return {@link List}
     * @throws PersistenciaException 
     */
    public List<Evento> listarEventos() throws PersistenciaException {
        return getEventoDao().listarEventos();
    }

    /**
     * Retorna os eventos que passaram do tempo da sua execução
     * @return {@link List}
     * @throws PersistenciaException 
     */
    public List<Evento> eventosRealizados() throws PersistenciaException {
        return getEventoDao().eventosRealizados();
    }
   
    /**
     * Retorna os eventos de acordo com o seu status 
     * @param status
     * @return {@link List}
     * @throws PersistenciaException 
     */
    public List<Evento> listarEventoStatus(String status) throws PersistenciaException {
        return getEventoDao().listarEventoStatus(status);
    }

    /**
     * Verifica se existe algum evento que expirou sua data
     * @return {@link boolean}
     * @throws PersistenciaException 
     */
    public boolean possuiEventosRealizados() throws PersistenciaException {
        return getEventoDao().possuiEventosRealizados();
    }
    /**
     * Retorna o Evento de acordo com o seu ID 
     * @param id
     * @return {@link Evento}
     * @throws PersistenciaException 
     */
    public Evento getEvento(Integer id) throws PersistenciaException{
        return getEventoDao().getEvento(id);
    }
    /**
     * Desaloca o Evento de acordo com seu ID passado 
     * @param id_evento
     * @throws PersistenciaException 
     */
    public void desalocar(Integer id_evento) throws PersistenciaException{
        getEventoDao().desalocar(id_evento);
    }
    
    /**
     * Muda o status do Evento passando o seu novo status e seu ID
     * @param id
     * @param status
     * @throws PersistenciaException 
     */
    
    public void mudarStatus(Integer id, String status) throws PersistenciaException{
        getEventoDao().mudarStatus(id, status);
    }
    
    /**
     * Desaloca todos os Eventos com o ID da sala passado
     * @param id_sala
     * @throws PersistenciaException 
     */
    public void desalocarPelaSala(Integer id_sala) throws PersistenciaException{
         getEventoDao().desalocarPelaSala(id_sala);
     }
}
