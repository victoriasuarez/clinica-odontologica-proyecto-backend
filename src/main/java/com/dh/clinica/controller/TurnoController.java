package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/buscarTodos")
    public List<TurnoDTO> buscarTodos() {
        return turnoService.buscarTodos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnoDto) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<TurnoDTO> respuesta;
        PacienteDTO paciente = pacienteService.buscarPorId(turnoDto.getPaciente().getId());
        OdontologoDTO odontologo = odontologoService.buscarPorId(turnoDto.getOdontologo().getId());
        if (paciente != null && odontologo != null) {
            respuesta = ResponseEntity.ok(turnoService.guardar(turnoDto));
        } else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }


    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        TurnoDTO turnoBuscado = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turnoBuscado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoDTO turnoDto) throws ResourceNotFoundException {
        TurnoDTO turnoActualizado = turnoService.actualizar(turnoDto);
        return ResponseEntity.ok(turnoActualizado);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        if(turnoService.buscarPorId(id)!=null){
            turnoService.eliminar(id);
        }
    }


}
