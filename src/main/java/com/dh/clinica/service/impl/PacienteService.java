package com.dh.clinica.service.impl;

import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper objectMapper;

    static final Logger logger = Logger.getLogger(PacienteService.class);

    @Override
    public List<PacienteDTO> buscarTodos(){
        List<PacienteDTO> respuesta;
        List<Paciente> listaDePacientes = pacienteRepository.findAll();
        respuesta = objectMapper.convertValue(listaDePacientes, objectMapper.getTypeFactory().constructCollectionType(List.class, PacienteDTO.class));
        return respuesta;
    }

    @Override
    public PacienteDTO guardar(PacienteDTO pacienteDto) throws BadRequestException {
        if (pacienteDto.getNombre() == null || pacienteDto.getApellido() == null || pacienteDto.getDni() == null || pacienteDto.getFechaIngreso() == null || pacienteDto.getDomicilio() == null) {
            logger.error("Se produjo un error al guardar el paciente");
            throw new BadRequestException("No se pudo guardar el paciente, faltan datos");
        }
        Paciente pacienteGuardado = objectMapper.convertValue(pacienteDto, Paciente.class);
        Paciente paciente = pacienteRepository.save(pacienteGuardado);
        return objectMapper.convertValue(paciente, PacienteDTO.class);
    }

    @Override
    public PacienteDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        //opcion 1: return pacienteRepository.findById(id).get();
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return objectMapper.convertValue(paciente.get(), PacienteDTO.class);
        }
        else throw new ResourceNotFoundException("No se encontro el paciente con id: " + id);
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO pacienteDto) throws ResourceNotFoundException {
        if (buscarPorId(pacienteDto.getId()) == null) {
            logger.error("Se produjo un error al actualizar el paciente");
            throw new ResourceNotFoundException("No se encontro el paciente con id: " + pacienteDto.getId());
        }
        logger.info("Se actualizo el paciente con id: " + pacienteDto.getId());
        Paciente paciente = objectMapper.convertValue(pacienteDto, Paciente.class);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return objectMapper.convertValue(pacienteGuardado, PacienteDTO.class);
    }

    @Override
    public void eliminar(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Se elimino el paciente con id: " + id);
        } else throw new ResourceNotFoundException("No se encontro el paciente con id: " + id);
    }

    //otra opcion de buscar por id
    //public Optional<Paciente> buscarPorId(Integer id){
    //return pacienteRepository.findById(id);
    //}



}
