package com.muaythaiapp.muaythai.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "tb_usuario")
public class ModeloUsuario {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private Integer idade;

    @Column(unique = true)
    private String usuario;
    private String password;

    public ModeloUsuario(){

    }
    public ModeloUsuario(String nome, Integer idade, String usuario, String password) {
        this.nome = nome;
        this.idade = idade;
        this.usuario = usuario;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
