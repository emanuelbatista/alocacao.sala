package com.br.ifpb.facade;

import com.br.ifpb.value.object.Evento;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsável por abstrair a geração dos Eventos
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 05/02/2015 às 08:20:15
 */
public class GerarEventoFacade{

   /**
    * Gerar uma Lista de Eventos contendo os eventos de acordo com suas respectivas 
    * repetições nas semanas 
    * @param nome
    * @param descricao
    * @param dataInicio
    * @param dataFinal
    * @param responsavel
    * @param totalParticipantes
    * @param repiticoes
    * @return {@link List}
    */
    public List<Evento> listarEvento(String nome, String descricao, Timestamp dataInicio,
            Timestamp dataFinal, String responsavel, Integer totalParticipantes, Integer repiticoes) {
        List<Evento> eventos=new LinkedList<>();
        for (int i = 0; i < repiticoes; i++) {
            Evento evento=new Evento();
            evento.setNome(nome);
            evento.setDescricao(descricao);
            evento.setResponsavel(responsavel);
            evento.setStatus(Evento.STATUS_PENDENTE_LOCAL);
            evento.setTotalParticipantes(totalParticipantes);
            evento.setDataInicio(gerarDataAposUmSemana(dataInicio, i));
            evento.setDataFinal(gerarDataAposUmSemana(dataFinal, i));        
            eventos.add(evento);
            
        }
        return eventos.isEmpty()?null:eventos;
    }
    
    /**
     * Gera um {@link Timestamp} pulando semana(as) de acordo com <b>Total de Semanas</b>
     * @param data
     * @param totalSemanas
     * @return {@link Timestamp}
     */
    private Timestamp gerarDataAposUmSemana(Timestamp data,long totalSemanas){
        return Timestamp.valueOf(data.toLocalDateTime().plusWeeks(totalSemanas));
    }
}
