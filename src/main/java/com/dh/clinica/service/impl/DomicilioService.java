package com.dh.clinica.service.impl;

import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IDomicilioRepository;
import com.dh.clinica.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper objectMapper;

    static final Logger logger = Logger.getLogger(DomicilioService.class);

    @Override
    public List<DomicilioDTO> buscarTodos(){
        List<DomicilioDTO> respuesta;
        List<Domicilio> listaDomicilios = domicilioRepository.findAll();
        respuesta = objectMapper.convertValue(listaDomicilios, List.class);

        return respuesta;
    }

    @Override
    public DomicilioDTO guardar(DomicilioDTO domicilioDto) throws BadRequestException {
        if (domicilioDto.getCalle() == null || domicilioDto.getNumero() == null || domicilioDto.getLocalidad() == null || domicilioDto.getLocalidad() == null){
            logger.error("Se produjo un error al guardar el domicilio");
            throw new BadRequestException("No se puede guardar el domicilio, faltan datos");
        }
        Domicilio domicilioGuardado = objectMapper.convertValue(domicilioDto, Domicilio.class);
        Domicilio domicilio = domicilioRepository.save(domicilioGuardado);

        return objectMapper.convertValue(domicilio, DomicilioDTO.class);
    }

    @Override
    public DomicilioDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if (domicilio.isPresent()) {
            return objectMapper.convertValue(domicilio.get(), DomicilioDTO.class);
        }
        else throw new ResourceNotFoundException("No se encontro el domicilio con id: " + id);
    }

    @Override
    public DomicilioDTO actualizar(DomicilioDTO domicilioDto) throws ResourceNotFoundException{
        if (buscarPorId(domicilioDto.getId()) == null) {
            logger.error("Se produjo un error al actualizar el domicilio");
            throw new ResourceNotFoundException("No se encontro el domicilio con id: " + domicilioDto.getId());
        }
        logger.info("Se actualizo el domicilio con id: " + domicilioDto.getId());
        Domicilio domicilioGuardado = objectMapper.convertValue(domicilioDto, Domicilio.class);
        Domicilio domicilio = domicilioRepository.save(domicilioGuardado);
        return objectMapper.convertValue(domicilio, DomicilioDTO.class);
    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if (domicilio.isPresent()) {
            domicilioRepository.deleteById(id);
            logger.info("Se elimino el domicilio con id: " + id);
        } else throw new ResourceNotFoundException("No se encontro el domicilio con id: " + id);
    }

}
