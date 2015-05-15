package com.br.ifpb.persiste.dao.factory;

/** 
 * Classe que gerencia a Fábrica de DAO
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 às 20:07:29
 */
public class DaoFactory {

    /**
     * Esse método retorna a Fábrica de DAO  
     * @return {@link DaoFactoryIf} 
     */
    public static DaoFactoryIf createFactory(){
        return new DaoFactoryBd();
    }
}
