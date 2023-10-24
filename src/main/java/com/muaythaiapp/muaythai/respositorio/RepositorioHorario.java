package com.muaythaiapp.muaythai.respositorio;

import com.muaythaiapp.muaythai.marcarhorario.ModeloHorario;
import com.muaythaiapp.muaythai.usuario.ModeloUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface RepositorioHorario extends JpaRepository<ModeloHorario, UUID> {

}
