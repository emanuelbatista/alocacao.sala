package com.br.ifpb.persiste.conexao.banco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Essa Classe é responsável pelo gerenciamento do arquivo de propriedade 
 * que contém as informações sobre a conexão com o banco de dados
 * @author Emanuel
 */
public class PropriedadeBanco {
    private static PropriedadeBanco instance;
    private Properties prop;

    private PropriedadeBanco() {
        this.prop = new Properties();
        loadProperties();
    }
    
    public static PropriedadeBanco getInstance(){
        if(instance==null){
            instance=new PropriedadeBanco();
        }
        return instance;
    }
    
    /**
     * Retorna o valor de acordo com a chave passado por parâmetro
     * @param key
     * @return {@link String}
     */
    public String getString(String key) {
	String value = this.prop.getProperty(key);
	return value;
    }
    
    /**
     * Escreve a chave e o valor no arquivo de propriedade
     * @param key
     * @param value 
     */
    public void putString(String key, String value) {
    	this.prop.setProperty(key, value);
    	updateProperties();
    }
    
    private void loadProperties() {
    	InputStream inputStream = null;
    	try { 
                File f=new File(getClass().getResource("/conexaoBanco.properties").getPath());
    		inputStream = new FileInputStream(f);
    		prop.load(inputStream);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void updateProperties() {
    	OutputStream out;
    	try {
    		out = new FileOutputStream("conexaoBanco.properties");
    		this.prop.store(out, null);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
   
   

