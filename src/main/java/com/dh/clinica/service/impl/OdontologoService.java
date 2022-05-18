package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper objectMapper;

    static final Logger logger = Logger.getLogger(OdontologoService.class);

    @Override
    public List<OdontologoDTO> buscarTodos(){
        List<OdontologoDTO> respuesta;
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        respuesta = objectMapper.convertValue(listaOdontologos, List.class);
        return respuesta;
    }

    @Override
    public OdontologoDTO guardar(OdontologoDTO odontologoDto) throws BadRequestException {
        if (odontologoDto.getNombre() == null || odontologoDto.getApellido() == null || odontologoDto.getMatricula() == null){
            logger.error("Se produjo un error al guardar el odontologo");
            throw new BadRequestException("No se pudo guardar el odontologo, faltan datos");
        }
        Odontologo odontologoGuardado = objectMapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologo = odontologoRepository.save(odontologoGuardado);
        return objectMapper.convertValue(odontologo, OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()){
            return objectMapper.convertValue(odontologo, OdontologoDTO.class);
        }
        else throw new ResourceNotFoundException("No se encontro el odontologo con id: " + id);
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO odontologoDto) throws ResourceNotFoundException {
        if (buscarPorId(odontologoDto.getId()) == null){
            logger.error("Se produjo un error al actualizar el odontologo");
            throw new ResourceNotFoundException("No se encontro el odontologo con id: " + odontologoDto.getId());
        }
        logger.info("Se actualizo el odontologo con id: " + odontologoDto.getId());
        Odontologo odontologo = objectMapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        return objectMapper.convertValue(odontologoGuardado, OdontologoDTO.class);

    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        if (odontologoBuscado.isPresent()) {
            odontologoRepository.deleteById(id);
            logger.info("Se elimino el odontologo con id: " + id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar el odontolgo con id:" + id);
        }
    }

}
