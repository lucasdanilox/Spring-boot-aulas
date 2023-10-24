package com.muaythaiapp.muaythai.controladores;

import com.muaythaiapp.muaythai.marcarhorario.ModeloHorario;
import com.muaythaiapp.muaythai.respositorio.RepositorioHorario;
import com.muaythaiapp.muaythai.usuarioDTO.UpdateHorarioDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/horario")
public class HorarioControlador extends ModeloHorario {

    @Autowired
    private RepositorioHorario repositorioHorario;

    @PostMapping("")
    public ResponseEntity<Object> marcarAula(@RequestBody ModeloHorario modeloHorario, HttpServletRequest request) {
        var id = request.getAttribute("id");
        modeloHorario.setId((UUID) id);

        var data = LocalDateTime.now();
        if (data.isAfter(modeloHorario.getHorario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser maior que a atual.");
        }
        var horario = this.repositorioHorario.save(modeloHorario);
        return ResponseEntity.status(HttpStatus.OK).body(horario);
    }

    @GetMapping("/aulas")
    public List<ModeloHorario> list() {
        return repositorioHorario.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> aulasId(@PathVariable(value = "id") UUID id) {
        Optional<ModeloHorario> aulas = repositorioHorario.findById(id);
        return aulas.<ResponseEntity<Object>>map(modeloHorario -> ResponseEntity.status(HttpStatus.OK)
                .body(modeloHorario)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aula não Existente."));
    }

    @PutMapping("/atualizacao/{id}")
    public ResponseEntity<Object> editarAulas(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Validated UpdateHorarioDto updateHorarioDto) {
        Optional<ModeloHorario> aulas = repositorioHorario.findById(id);
        if (aulas.isPresent()) {
            ModeloHorario attDados = aulas.get();
            if (updateHorarioDto.getNome() != null) {
                attDados.setNome(updateHorarioDto.getNome());
            }
            if (updateHorarioDto.getTipoLuta() != null) {
                attDados.setTipoLuta(updateHorarioDto.getTipoLuta());
            }
            if (updateHorarioDto.getHorario() != null) {
                attDados.setHorario(updateHorarioDto.getHorario());
            }
            repositorioHorario.save(attDados);
            return ResponseEntity.ok(attDados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não existente.");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletarAulas (@PathVariable(value = "id") UUID id){
        Optional<ModeloHorario> deleteAulas = repositorioHorario.findById(id);
        if(deleteAulas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aula não Existente.");
        }
        repositorioHorario.delete(deleteAulas.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aula deletada Com Sucesso.");
    }


}
