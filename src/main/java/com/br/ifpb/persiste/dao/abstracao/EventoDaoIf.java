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
 *
 * @author Emanuel
 */
public interface EventoDaoIf {

    void adicionar(Evento... eventos) throws PersistenciaException;
    
    List<Evento> listarEventos() throws PersistenciaException;

    void mudarStatus(Integer id, String status) throws PersistenciaException;

    void alocar(Integer id_sala, Evento... evento) throws PersistenciaException;

    void desalocar(Integer id_evento) throws PersistenciaException;

    List<Sala> listarSalasDisponiveisEvento(Evento... eventos) throws PersistenciaException;

    List<Evento> buscarEvento(String nome, String descricao, Timestamp data, String responsavel, String... status) throws PersistenciaException;
  
    List<Evento> eventosRealizados() throws PersistenciaException;
    
    List<Evento> listarEventoStatus(String status) throws PersistenciaException;
    
    boolean possuiEventosRealizados() throws PersistenciaException;
    
    Evento getEvento(Integer id) throws PersistenciaException;

}
