/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.persiste.dao.abstracao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.value.object.Sala;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface SalaDaoIf {

    void adicionar(Sala sala) throws PersistenciaException;

    void editar(Sala sala) throws PersistenciaException;

    void remover(Integer id) throws PersistenciaException;
    
    Sala getSala(Integer id) throws PersistenciaException;

    List<Sala> listar() throws PersistenciaException;  
    
    boolean existeIdentificacao(String identificacao) throws PersistenciaException;
    
}
