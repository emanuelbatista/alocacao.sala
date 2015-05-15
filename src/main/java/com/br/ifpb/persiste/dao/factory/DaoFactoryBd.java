package com.br.ifpb.persiste.dao.factory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.EventoDaoIf;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;
import com.br.ifpb.persiste.dao.implementacao.EventoDao;
import com.br.ifpb.persiste.dao.implementacao.SalaDao;

/**
 * Classe que representa a Fábrica de DAO de Banco de Dados PostgreSQL
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 às 20:05:33
 */
public class DaoFactoryBd implements DaoFactoryIf {

    /**
     * Retorna a DAO de Sala
     * @return {@link SalaDaoIf}
     * @throws PersistenciaException 
     */
    @Override
    public SalaDaoIf createSalaDao() throws PersistenciaException {
        return new SalaDao();
    }

    /**
     * Retorna o DAO de Evento
     * @return {@link EventoDaoIf}
     * @throws PersistenciaException 
     */
    @Override
    public EventoDaoIf createEventoDao() throws PersistenciaException {
        return new EventoDao();
    }

}
