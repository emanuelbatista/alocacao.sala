/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.persiste.dao.abstracao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe que faz todos as ações responsável por {@link Evento} fazendo a comunicação com a base de dados  
 * @author Emanuel
 */
public interface EventoDaoIf {
    
    /**
     * Adiciona um Evento
     * @param eventos
     * @throws PersistenciaException 
     */
    void adicionar(Evento... eventos) throws PersistenciaException;
    
    /**
     * Lista todos os Eventos
     * @return {@linkplain List}
     * @throws PersistenciaException 
     */
    List<Evento> listarEventos() throws PersistenciaException;
    
    /**
     * Muda o status do Evento 
     * @param id
     * @param status
     * @throws PersistenciaException 
     */
    void mudarStatus(Integer id, String status) throws PersistenciaException;
    
    /**
     * Aloca a Sala ao Eventos
     * @param id_sala
     * @param evento
     * @throws PersistenciaException 
     */
    void alocar(Integer id_sala, Evento... evento) throws PersistenciaException;
    
    /**
     * Desaloca a Sala ao Evento
     * @param id_evento
     * @throws PersistenciaException 
     */
    void desalocar(Integer id_evento) throws PersistenciaException;
    
    /**
     * Lista todas as Salas Disponíveis de Acordo com os eventos passados por parâmetro
     * @param eventos
     * @return {@link List}
     * @throws PersistenciaException 
     */
    List<Sala> listarSalasDisponiveisEvento(Evento... eventos) throws PersistenciaException;

    /**
     * Busca os Evento de Acordo com os parâmetro passado
     * @param nome
     * @param descricao
     * @param data
     * @param responsavel
     * @param status
     * @return {@link List}
     * @throws PersistenciaException 
     */
    List<Evento> buscarEvento(String nome, String descricao, Timestamp data, String responsavel, String... status) throws PersistenciaException;
    
    /**
     * Lista todos os eventos que passaram do tempo de sua realização
     * @return {@link List}
     * @throws PersistenciaException 
     */
    List<Evento> eventosRealizados() throws PersistenciaException;
    
    /**
     * Lista os eventos de acordo com o status passado por parâmetro
     * @param status
     * @return {@link List}
     * @throws PersistenciaException 
     */
    List<Evento> listarEventoStatus(String status) throws PersistenciaException;
    
    /**
     * Verifica se existe eventos que passaram do prazo de sua realização
     * @return boolean
     * @throws PersistenciaException 
     */
    boolean possuiEventosRealizados() throws PersistenciaException;
    
    /**
     * Retorna um evento de acordo com seu ID
     * @param id
     * @return {@link Evento}
     * @throws PersistenciaException 
     */
    Evento getEvento(Integer id) throws PersistenciaException;
    
    /**
     * Desaloca todos os eventos que possuem a Sala com ID igual ao do passado por
     * parâmetro
     * @param id_sala
     * @throws PersistenciaException 
     */
    void desalocarPelaSala(Integer id_sala) throws PersistenciaException;

}
