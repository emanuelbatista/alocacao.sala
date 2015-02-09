/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
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
    public String getString(String key) {
	String value = this.prop.getProperty(key);
	return value;
    }
    
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
   
   

