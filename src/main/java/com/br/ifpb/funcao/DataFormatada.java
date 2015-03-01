package com.br.ifpb.funcao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que é responsável por gerencia a converção de data e/ou hora para String
 * @author Emanuel
 */
public class DataFormatada {

    /**
     * Converte {@link Timestamp} em {@link String} no formato dd/MM/yyyy HH:mm
     * @param data
     * @return {@link String}
     */
    public static String formatarData(Timestamp data) {
        LocalDateTime localDateTime = data.toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(dateTimeFormatter) + " às " + localDateTime.format(dateTimeFormatter1);
    }

    /**
     * Converte {@link Date} em {@link String} no formato dd/MM/yyy
     * @param data
     * @return {@link String}
     */
    public static String formatarData(Date data) {
        LocalDate localDateTime = data.toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateTime.format(dateTimeFormatter);
    }

}
