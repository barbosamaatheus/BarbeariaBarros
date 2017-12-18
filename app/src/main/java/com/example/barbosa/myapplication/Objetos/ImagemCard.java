package com.example.barbosa.myapplication.Objetos;

/**
 * Created by User on 17/12/2017.
 */

public class ImagemCard {

    private int Imagem;
    private String titulo;
    private String descricao;

    public ImagemCard() {
    }

    public ImagemCard(int imagem, String titulo, String descricao) {
        Imagem = imagem;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getImagem() {
        return Imagem;
    }

    public void setImagem(int imagem) {
        Imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
