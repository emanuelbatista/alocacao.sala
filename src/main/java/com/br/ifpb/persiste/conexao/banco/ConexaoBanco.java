package com.br.ifpb.persiste.conexao.banco;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que representa a conexão com o banco de dados
 *
 * @author emanuel
 * @author DouglasGabriel
 */
public class ConexaoBanco {

    /**
     * Esse método retorna a conexão com o banco de dados
     *
     * @return {@link Connection}
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        // Forma de conectar-se ao banco de dados Heroku
        String username, password;
        try {
            Connection con = null;
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            int port = dbUri.getPort();
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();
            if ((con = DriverManager.getConnection(dbUrl, username, password)) != null) {
                return con;
            }
        } catch (Exception e) {
            // Forma de conectar-se ao banco de dados local
            PropriedadeBanco propriedadeBanco = PropriedadeBanco.getInstance();
            Class.forName(propriedadeBanco.getString("drive"));
            String url = propriedadeBanco.getString("url");
            username = propriedadeBanco.getString("user");
            password = propriedadeBanco.getString("password");
            return DriverManager.getConnection(url, username, password);
        }
        return null;
    }
}
