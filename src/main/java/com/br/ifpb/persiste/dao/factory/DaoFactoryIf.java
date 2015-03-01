package com.br.ifpb.persiste.dao.factory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.EventoDaoIf;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;

/**
 * Interface que representa a Fabrica de DAO genérico
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
public interface DaoFactoryIf {
    
    /**
     * Retorna o DAO de Sala
     * @return {@link SalaDaoIf}
     * @throws PersistenciaException 
     */
    public SalaDaoIf createSalaDao() throws PersistenciaException;
    
    /**
     * Retorna o DAO de Evento
     * @return {@link EventoDaoIf}
     * @throws PersistenciaException 
     */
    public EventoDaoIf createEventoDao() throws PersistenciaException;
    
}
