package com.br.ifpb.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converte Informações
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 24/02/2015 às 20:43:39
 */
public class ConverterInformacao {

    /**
     * Converte uma {@link String} em {@link Timestamp} no formato dd/MM/yyyy
     * HH:mm
     * @param data
     * @return {@link Timestamp}
     * @throws DateTimeParseException
     */
    public static Timestamp converteTimestamp(String data) throws DateTimeParseException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDate = LocalDateTime.parse(data, format);
            return Timestamp.valueOf(localDate);
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Data Formato Invalido", null, 0);
        }
    }
}
