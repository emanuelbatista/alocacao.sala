package com.br.ifpb.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 24/02/2015 ás 20:43:39
 */
public class ConverterInformacao {

    public static Timestamp converteTimestamp(String data) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, format);
        return Timestamp.valueOf(localDate);
    }
}
