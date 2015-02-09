package com.br.ifpb.persiste.dao.factory;

/** 
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015 ás 20:07:29
 */
public class DaoFactory {

    public static DaoFactoryIf createFactory(){
        return new DaoFactoryBd();
    }
}
