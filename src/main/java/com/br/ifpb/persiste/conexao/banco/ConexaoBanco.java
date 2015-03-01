package com.br.ifpb.persiste.conexao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que representa a conexão com o banco de dados
 * @author Emanuel
 */
public class ConexaoBanco {
    
    /**
     * Esse método retorna a conexão com o banco de dados 
     * @return {@link Connection}
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static  Connection getConexao() throws ClassNotFoundException, SQLException{
        PropriedadeBanco propriedadeBanco=PropriedadeBanco.getInstance();
        Class.forName("org.postgresql.Driver");
        String url=propriedadeBanco.getString("url");
        String user=propriedadeBanco.getString("user");
        String password=propriedadeBanco.getString("password");
        return DriverManager.getConnection(url, user, password);
    }
}
