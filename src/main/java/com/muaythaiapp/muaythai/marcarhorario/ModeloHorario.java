package com.muaythaiapp.muaythai.marcarhorario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_horario")
public class ModeloHorario {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String tipoLuta;

    @CreationTimestamp
    private LocalDateTime horario;
    private String nome;

    @Column(unique = true)
    private String usuario;

    public ModeloHorario(){

    }
    public ModeloHorario(UUID id, String tipoLuta, LocalDateTime horario, String nome) {
        this.id = id;
        this.tipoLuta = tipoLuta;
        this.horario = horario;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
