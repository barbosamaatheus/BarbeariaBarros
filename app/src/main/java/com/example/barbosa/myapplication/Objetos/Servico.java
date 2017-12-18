package com.example.barbosa.myapplication.Objetos;

public class Servico {

    private String nome;
    private String descricao;
    private String preco;


    public Servico(String nome, String descricao, String preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;

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
}

