package com.br.ifpb.persiste.dao.factory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.EventoDaoIf;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;
import com.br.ifpb.persiste.dao.implementacao.EventoDao;
import com.br.ifpb.persiste.dao.implementacao.SalaDao;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 ás 20:05:33
 */
public class DaoFactoryBd implements DaoFactoryIf {

    @Override
    public SalaDaoIf createSalaDao() throws PersistenciaException {
        return new SalaDao();
    }

    @Override
    public EventoDaoIf createEventoDao() throws PersistenciaException {
        return new EventoDao();
    }

}
