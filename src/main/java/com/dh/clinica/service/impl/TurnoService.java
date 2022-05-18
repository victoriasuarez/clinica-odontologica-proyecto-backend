package com.dh.clinica.service.impl;

import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.entities.Turno;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    ObjectMapper objectMapper;

    static final Logger logger = Logger.getLogger(TurnoService.class);

    @Override
    public List<TurnoDTO> buscarTodos(){
        List<TurnoDTO> respuesta;
        List<Turno> turnos = turnoRepository.findAll();
        respuesta = objectMapper.convertValue(turnos, List.class);
        return respuesta;
    }

    @Override
    public TurnoDTO guardar(TurnoDTO turnoDto) throws BadRequestException {
        if(turnoDto.getOdontologo() == null || turnoDto.getPaciente() == null || turnoDto.getFecha() == null || turnoDto.getHora() == null){
            logger.error("Se ha producido un error al guardar el turno");
            throw new BadRequestException("No se puede guardar un turno sin odontologo, paciente, fecha y hora");
        }
        Turno turnoGuardado = objectMapper.convertValue(turnoDto, Turno.class);
        Turno turno = turnoRepository.save(turnoGuardado);
        return objectMapper.convertValue(turno, TurnoDTO.class);
    }

    @Override
    public TurnoDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return objectMapper.convertValue(turnoBuscado.get(), TurnoDTO.class);
        }
        else throw new ResourceNotFoundException("No se ha encontrado el turno con id: " + id);
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        if (buscarPorId(turnoDTO.getId()) == null) {
            logger.error("Se ha producido un error al actualizar el turno");
            throw new ResourceNotFoundException("No se ha encontrado el turno con id: " + turnoDTO.getId());
        }
        Turno turno = objectMapper.convertValue(turnoDTO, Turno.class);
        Turno turnoGuardado = turnoRepository.save(turno);
        return objectMapper.convertValue(turnoGuardado, TurnoDTO.class);
    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            turnoRepository.deleteById(id);
            logger.info("Se ha eliminado el turno con id: " + id);
        } else throw new ResourceNotFoundException("No se ha encontrado el turno con id: " + id);
    }

}
