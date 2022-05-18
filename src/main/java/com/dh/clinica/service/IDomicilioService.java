package com.dh.clinica.service;

import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface IDomicilioService {

    List<DomicilioDTO> buscarTodos();
    DomicilioDTO guardar(DomicilioDTO domicilioDto) throws BadRequestException;
    DomicilioDTO buscarPorId(Integer id) throws ResourceNotFoundException;
    DomicilioDTO actualizar(DomicilioDTO domicilioDto) throws ResourceNotFoundException;
    void eliminar(Integer id) throws ResourceNotFoundException;

}
