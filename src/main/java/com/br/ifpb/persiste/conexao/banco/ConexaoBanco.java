/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.persiste.conexao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Emanuel
 */
public class ConexaoBanco {
    public static  Connection getConexao() throws ClassNotFoundException, SQLException{
        PropriedadeBanco propriedadeBanco=PropriedadeBanco.getInstance();
        Class.forName("org.postgresql.Driver");
        String url=propriedadeBanco.getString("url");//"jdbc:postgresql://localhost:5432/cadastroAluno";
        String user=propriedadeBanco.getString("user");//"postgres";
        String password=propriedadeBanco.getString("password");//"yadirf";
        return DriverManager.getConnection(url, user, password);
    }
}
