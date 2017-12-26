package com.example.barbosa.myapplication.Objetos;

import android.content.Context;



import com.firebase.client.Firebase;

/**
 * Created by User on 16/12/2017.
 */

public class Cliente {

    private static final String TOKEN = "com.example.barbosa.myapplication.Objetos.Cliente.PROVIDER";
    private String codigo;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String pontos;

    public Cliente(String codigo, String nome, String email, String telefone, String senha, String pontos) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.pontos = pontos;
    }
    public Cliente(){}

    public Cliente(String email, String nome, String pontos, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.pontos = pontos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", pontos='" + pontos + '\'' +
                '}';
    }
}
