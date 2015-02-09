package com.br.ifpb.facade;

import com.br.ifpb.value.object.Evento;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 05/02/2015 ás 08:20:15
 */
public class GerarEventoFacade{

    private enum EventoConst{
        STATUS_DEFAULT("Pendente de Local");
        
        private String value;

        private EventoConst(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        
        
        
    }
   
    public List<Evento> listarEvento(String nome, String descricao, Timestamp dataInicio,
            Timestamp dataFinal, String responsavel, Integer totalParticipantes, Integer repiticoes) {
        List<Evento> eventos=new LinkedList<>();
        for (int i = 0; i < repiticoes; i++) {
            Evento evento=new Evento();
            evento.setNome(nome);
            evento.setDescricao(descricao);
            evento.setResponsavel(responsavel);
            evento.setStatus(EventoConst.STATUS_DEFAULT.getValue());
            evento.setTotalParticipantes(totalParticipantes);
            evento.setDataInicio(gerarDataAposUmSemana(dataInicio, i));
            evento.setDataFinal(gerarDataAposUmSemana(dataFinal, i));        
            eventos.add(evento);
            
        }
        return eventos.isEmpty()?null:eventos;
    }
    
    private Timestamp gerarDataAposUmSemana(Timestamp data,long totalSemanas){
        return Timestamp.valueOf(data.toLocalDateTime().plusWeeks(totalSemanas));
    }
}
