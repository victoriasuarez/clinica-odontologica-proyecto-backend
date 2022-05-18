package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/buscarTodos")
    public List<OdontologoDTO> buscarTodos(){
        return odontologoService.buscarTodos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO odontologoDto) throws BadRequestException {
        OdontologoDTO odontologoPublicado = odontologoService.guardar(odontologoDto);
        return ResponseEntity.ok(odontologoPublicado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        OdontologoDTO odontologoBuscado = odontologoService.buscarPorId(id);
        return ResponseEntity.ok(odontologoBuscado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO odontologo) throws ResourceNotFoundException {
        OdontologoDTO odontologoActualizado = odontologoService.actualizar(odontologo);
        return ResponseEntity.ok(odontologoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
            if(odontologoService.buscarPorId(id)!=null){
                odontologoService.eliminar(id);
            }
    }
}
