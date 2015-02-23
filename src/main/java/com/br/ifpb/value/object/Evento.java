package com.br.ifpb.value.object;

import java.sql.Timestamp;

/**
 *
 * @author Emanuel
 */
public class Evento {
    public static final String STATUS_ALOCADO="Alocado";
    public static final String STATUS_REALIZADO="Realizado";
    public static final String STATUS_CANCELADO="Cancelado";
    public static final String STATUS_PENDENTE_LOCAL="Pendente de Local";
    private Integer id;
    private String nome;
    private String descricao;
    private Timestamp dataInicio;
    private Timestamp dataFinal;
    private String responsavel;
    private Integer totalParticipantes;
    private String status;
    private Sala sala;

    public Evento() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Timestamp dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getTotalParticipantes() {
        return totalParticipantes;
    }

    public void setTotalParticipantes(Integer totalParticipantes) {
        this.totalParticipantes = totalParticipantes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    
}
