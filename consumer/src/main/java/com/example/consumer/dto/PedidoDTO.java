package com.example.consumer.dto;

public class PedidoDTO {
    private String produto;
    private Integer quantidade;
    private Double valor;

    public PedidoDTO() {
    }

    public PedidoDTO(String produto, Integer quantidade, Double valor) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
} 