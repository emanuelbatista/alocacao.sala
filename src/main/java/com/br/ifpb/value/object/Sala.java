package com.br.ifpb.value.object;

/**
 * Essa classe tem o papel de representar uma <b>Sala</b> no sistema
 * @author Emanuel
 */
public class Sala {

    private String tipo;
    private Integer id;
    private Integer capacidade;
    private String identificacao;
    private String apelido;

    public Sala() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apleido) {
        this.apelido = apleido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
