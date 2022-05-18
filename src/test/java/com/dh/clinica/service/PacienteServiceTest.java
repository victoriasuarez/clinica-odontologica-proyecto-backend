package com.dh.clinica.service;

import com.dh.clinica.dto.PacienteDTO;
import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.PacienteService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class PacienteServiceTest {

    PacienteService pacienteService;

    @Test
    public void buscarTodosLosPacientes() {
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);

    }

    @Test
    public void guardarPacientes() throws BadRequestException {
        PacienteDTO pacienteDto = pacienteService.guardar(new PacienteDTO("Gabriela","Diaz", "168464654", new Date(2022,04,14), new Domicilio()));
        Assert.assertTrue(pacienteDto.getId() != null);
    }

    @Test
    public void buscarPacientePorId() throws ResourceNotFoundException {
        PacienteDTO pacienteDto = pacienteService.buscarPorId(1);
        Assert.assertTrue(pacienteDto.getId() == null);
    }

    @Test
    public void actualizarPaciente() throws ResourceNotFoundException {
        PacienteDTO pacienteDto = pacienteService.buscarPorId(1);
        pacienteDto.setNombre("Gabriela");
        pacienteDto.setApellido("Perez");
        pacienteDto.setDni("123456789");
        pacienteService.actualizar(pacienteDto);
        Assert.assertTrue(pacienteService.buscarPorId(1).getNombre().equals("Gabriela"));
        Assert.assertTrue(pacienteService.buscarPorId(1).getApellido().equals("Perez"));
        Assert.assertTrue(pacienteService.buscarPorId(1).getDni().equals("123456789"));
    }

    @Test
    public void eliminarPaciente() throws ResourceNotFoundException {
        pacienteService.eliminar(1);
        Assert.assertTrue(pacienteService.buscarPorId(1) == null);
    }
}