package com.dh.clinica.controller;

import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/buscarTodos")
    public List<PacienteDTO> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO pacienteDto) throws BadRequestException {
        PacienteDTO pacientePublicado = pacienteService.guardar(pacienteDto);
        return ResponseEntity.ok(pacientePublicado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        PacienteDTO pacienteBuscado = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteBuscado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacienteDto) throws ResourceNotFoundException {
        PacienteDTO pacienteActualizado = pacienteService.actualizar(pacienteDto);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
            if(pacienteService.buscarPorId(id)!=null){
                pacienteService.eliminar(id);
            }

        }
}
