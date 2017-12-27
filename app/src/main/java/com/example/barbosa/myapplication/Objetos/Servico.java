package com.example.barbosa.myapplication.Objetos;

public class Servico {

    private String nome;
    private String descricao;
    private String preco;
    private String valorPts;

    public Servico() {
    }

    public Servico(String nome, String descricao, String preco, String valorPts) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.valorPts = valorPts;

    }

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

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPreco() {
        return preco;
    }

    public String getValorPts() {
        return valorPts;
    }

    public void setValorPts(String valorPts) {
        this.valorPts = valorPts;
    }
}

