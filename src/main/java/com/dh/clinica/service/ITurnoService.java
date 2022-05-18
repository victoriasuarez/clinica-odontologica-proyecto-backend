package com.dh.clinica.service;

import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    List<TurnoDTO> buscarTodos();
    TurnoDTO guardar(TurnoDTO turnoDto) throws BadRequestException;
    TurnoDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    TurnoDTO actualizar(TurnoDTO turnoDto) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;

}
