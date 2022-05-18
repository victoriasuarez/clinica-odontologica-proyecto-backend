package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    List<OdontologoDTO> buscarTodos();
    OdontologoDTO guardar(OdontologoDTO odontologoDto) throws BadRequestException;
    OdontologoDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    OdontologoDTO actualizar(OdontologoDTO odontologoDto) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;

}
