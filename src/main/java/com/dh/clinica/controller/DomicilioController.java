package com.dh.clinica.controller;

import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping("/buscarTodos")
    public List<DomicilioDTO> buscarTodos(){
        return domicilioService.buscarTodos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<DomicilioDTO> registrarDomicilio(@RequestBody DomicilioDTO domicilioDto) throws BadRequestException {
        DomicilioDTO domicilioPublicado = domicilioService.guardar(domicilioDto);
        return ResponseEntity.ok(domicilioPublicado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioDTO> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        DomicilioDTO domicilioBuscado = domicilioService.buscarPorId(id);
        return ResponseEntity.ok(domicilioBuscado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioDTO> actualizar(@RequestBody DomicilioDTO domicilioDto) throws ResourceNotFoundException {
        DomicilioDTO domicilioActualizado = domicilioService.actualizar(domicilioDto);
        return ResponseEntity.ok(domicilioActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        if (domicilioService.buscarPorId(id) != null) {
            domicilioService.eliminar(id);
        }
    }
}
