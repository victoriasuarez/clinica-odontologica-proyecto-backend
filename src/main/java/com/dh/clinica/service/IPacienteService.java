package com.dh.clinica.service;

import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    List<PacienteDTO> buscarTodos();
    PacienteDTO guardar(PacienteDTO pacienteDto) throws BadRequestException;
    PacienteDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    PacienteDTO actualizar(PacienteDTO pacienteDto) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;

}
