package com.br.ifpb.business.object;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;
import com.br.ifpb.persiste.dao.factory.DaoFactory;
import com.br.ifpb.persiste.dao.factory.DaoFactoryIf;
import com.br.ifpb.value.object.Sala;

/** 
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 ás 19:46:48
 */
public class GerenciarSala {

    public GerenciarSala() {}
    
    private SalaDaoIf getSalaDao() throws PersistenciaException{
        DaoFactoryIf daoFactory=DaoFactory.createFactory();
        return daoFactory.createSalaDao();
    }
    public void adicionar(String identificacao,String apelido, String tipo, Integer capacidade) throws PersistenciaException{
        Sala sala=new Sala();
        sala.setIdentificacao(identificacao);
        sala.setApelido(apelido);
        sala.setTipo(tipo);
        sala.setCapacidade(capacidade);
        getSalaDao().adicionar(sala);
    }
    public Sala getSala(Integer id) throws PersistenciaException{
       return getSalaDao().getSala(id);
    }
   
}
