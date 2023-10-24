package com.muaythaiapp.muaythai.respositorio;

import com.muaythaiapp.muaythai.usuario.ModeloUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface RepositorioUsuario extends JpaRepository<ModeloUsuario, UUID> {

    ModeloUsuario findByusuario(String usuario);
}
