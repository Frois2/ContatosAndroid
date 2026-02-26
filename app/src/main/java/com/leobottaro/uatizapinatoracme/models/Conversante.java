package com.leobottaro.uatizapinatoracme.models;

public class Conversante {
    private int Id;
    private String Nome;
    private String Celular;
    private String Email;

    public Conversante(String nome, String celular, String email) {
        Nome = nome;
        Celular = celular;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
