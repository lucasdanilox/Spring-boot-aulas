package com.muaythaiapp.muaythai.usuarioDTO;

public class UpdateUsuarioDto {

    private String nome;
    private Integer idade;
    private String password;

    public UpdateUsuarioDto(){

    }

    public UpdateUsuarioDto(String nome, Integer idade, String password) {
        this.nome = nome;
        this.idade = idade;
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
}
