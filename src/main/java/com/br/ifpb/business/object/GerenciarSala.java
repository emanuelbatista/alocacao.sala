package com.br.ifpb.business.object;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;
import com.br.ifpb.persiste.dao.factory.DaoFactory;
import com.br.ifpb.persiste.dao.factory.DaoFactoryIf;
import com.br.ifpb.value.object.Sala;
import java.util.List;

/**
 * Responsável por gerenciar toda a regra de negocio sobre o {@link Sala}
 * ele repassa as informações do {@link Sala} para base de dados
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 às 19:46:48
 */
public class GerenciarSala {

    public GerenciarSala() {
    }

    /**
     *  Ele retorna a classe que persiste {@link Sala} 
     *  @return {@link SalaDaoIf} 
     */
    private SalaDaoIf getSalaDao() throws PersistenciaException {
        DaoFactoryIf daoFactory = DaoFactory.createFactory();
        return daoFactory.createSalaDao();
    }

    /**
     * Adicionar a {@link Sala} a base de dados
     * @param identificacao
     * @param apelido
     * @param tipo
     * @param capacidade
     * @throws PersistenciaException 
     */
    public void adicionar(String identificacao, String apelido, String tipo, Integer capacidade) throws PersistenciaException {
        Sala sala = new Sala();
        sala.setIdentificacao(identificacao);
        sala.setApelido(apelido);
        sala.setTipo(tipo);
        sala.setCapacidade(capacidade);
        getSalaDao().adicionar(sala);
    }

    /**
     * Retorna uma {@link Sala} de acordo com seu id 
     * @param id
     * @return {@link Sala}
     * @throws PersistenciaException 
     */
    public Sala getSala(Integer id) throws PersistenciaException {
        return getSalaDao().getSala(id);
    }

    /**
     * Lista todas as Salas salvas
     * @return {@linkplain List}
     * @throws PersistenciaException 
     */
    public List<Sala> listar() throws PersistenciaException {
        return getSalaDao().listar();
    }

    /**
     * Atualiza as informações de uma Sala de acordo com o ID
     * @param id
     * @param identificacao
     * @param apelido
     * @param tipo
     * @param capacidade
     * @throws PersistenciaException 
     */
    public void atualizar(Integer id,String identificacao, String apelido, String tipo, Integer capacidade) throws PersistenciaException {
        Sala sala = new Sala();
        sala.setId(id);
        sala.setIdentificacao(identificacao);
        sala.setApelido(apelido);
        sala.setTipo(tipo);
        sala.setCapacidade(capacidade);
        getSalaDao().atualizar(sala);
    }
    /**
     * Remove uma sala de acordo com o ID da Sala
     * @param id
     * @throws PersistenciaException 
     */
    public void remover(Integer id) throws PersistenciaException{
        getSalaDao().remover(id);
    }
    
    /**
     * Verifica se a Sala existe de acordo com sua identificação, se já existe retorna verdadeiro e falso caso não exista 
     * @param identificacao
     * @return {@linkplain boolean}
     * @throws PersistenciaException 
     */
    public boolean existeIdentificacao(String identificacao) throws PersistenciaException{
         return getSalaDao().existeIdentificacao(identificacao);
     }
}
