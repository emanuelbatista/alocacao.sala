/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.funcao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Emanuel
 */
public class DataFormatada {

    public static String formatarData(Timestamp data) {
        LocalDateTime localDateTime = data.toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(dateTimeFormatter) + " ás " + localDateTime.format(dateTimeFormatter1);
    }

    public static String formatarData(Date data) {
        LocalDate localDateTime = data.toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateTime.format(dateTimeFormatter);
    }

}
