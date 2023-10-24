package com.muaythaiapp.muaythai.usuarioDTO;

import java.time.LocalDateTime;

public class UpdateHorarioDto {

    private String tipoLuta;
    private LocalDateTime horario;
    private String nome;

    public UpdateHorarioDto(){

    }
    public UpdateHorarioDto(String tipoLuta, LocalDateTime horario, String nome) {
        this.tipoLuta = tipoLuta;
        this.horario = horario;
        this.nome = nome;
    }

    public String getTipoLuta() {
        return tipoLuta;
    }

    public void setTipoLuta(String tipoLuta) {
        this.tipoLuta = tipoLuta;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


