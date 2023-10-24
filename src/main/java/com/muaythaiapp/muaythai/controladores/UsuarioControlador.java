package com.muaythaiapp.muaythai.controladores;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.muaythaiapp.muaythai.respositorio.RepositorioUsuario;
import com.muaythaiapp.muaythai.service.ServiceUsuario;
import com.muaythaiapp.muaythai.usuario.ModeloUsuario;
import com.muaythaiapp.muaythai.usuarioDTO.UpdateUsuarioDto;
import com.muaythaiapp.muaythai.util.SenhaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("")
    public ResponseEntity<Object> criarUsuario(@RequestBody ModeloUsuario modeloUsuario) {

        var user = this.repositorioUsuario.findByusuario(modeloUsuario.getUsuario());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Existente.");
        }

        var cript = BCrypt.withDefaults().hashToString(12, modeloUsuario.getPassword().toCharArray());
        modeloUsuario.setPassword(cript);

        var userCriado = this.repositorioUsuario.save(modeloUsuario);
        Map<String, Object> response = new HashMap<>();
        response.put("id", userCriado.getId());
        response.put("nome", userCriado.getNome());
        response.put("idade", userCriado.getIdade());
        response.put("password", userCriado.getPassword());
        response.put("usuario", userCriado.getUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users")
    public List<ModeloUsuario> modeloUsuarioList() {
        return serviceUsuario.listModel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> userById(@PathVariable UUID id) {
        Optional<ModeloUsuario> user = repositorioUsuario.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não existente.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody UpdateUsuarioDto updateUsuarioDto) {
        Optional<ModeloUsuario> optionalModeloUsuario = repositorioUsuario.findById(id);

        if (optionalModeloUsuario.isPresent()) {
            ModeloUsuario usuario = optionalModeloUsuario.get();

            if (updateUsuarioDto.getNome() != null) {
                usuario.setNome(updateUsuarioDto.getNome());
            }
            if (updateUsuarioDto.getIdade() != null) {
                usuario.setIdade(updateUsuarioDto.getIdade());
            }
            if (updateUsuarioDto.getPassword() != null) {
                String novaSenha = SenhaUtil.criptografar(updateUsuarioDto.getPassword());
                usuario.setPassword(novaSenha);
            }
            repositorioUsuario.save(usuario);

            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não Existente.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<ModeloUsuario> optionalModeloUsuarioDelete = repositorioUsuario.findById(id);
        if (optionalModeloUsuarioDelete.isPresent()) {
            repositorioUsuario.deleteById(id);
            return ResponseEntity.ok("Usuario excluido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não Existente.");
        }

    }
}

