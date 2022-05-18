package com.dh.clinica.service;

import com.dh.clinica.dto.TurnoDTO;
import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class TurnoServiceTest {

    TurnoService turnoService;

    @Test
    public void buscarTodosLosTurnos() {
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        Assert.assertTrue(!turnos.isEmpty());
        Assert.assertTrue(turnos.size() > 0);
    }

    @Test
    public void guardarTurnos() throws BadRequestException {
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        TurnoDTO turnoDto = turnoService.guardar(new TurnoDTO(paciente, odontologo, fecha, hora));
        Assert.assertTrue(turnoDto.getId() != null);
    }

    @Test
    public void buscarTurnosPorId() throws ResourceNotFoundException {
        TurnoDTO turnoDto = turnoService.buscarPorId(1);
        Assert.assertTrue(turnoDto.getId() == 1);
    }

    @Test
    public void actualizarTurnos() throws ResourceNotFoundException {
        TurnoDTO turnoDto = turnoService.buscarPorId(1);
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setHora(LocalTime.now());
        turnoService.actualizar(turnoDto);
        Assert.assertTrue(turnoService.buscarPorId(1).getFecha().equals(turnoDto.getFecha()));
        Assert.assertTrue(turnoService.buscarPorId(1).getHora().equals(turnoDto.getHora()));
    }

    @Test
    public void eliminarTurnos() throws ResourceNotFoundException {
        turnoService.eliminar(1);
        Assert.assertTrue(turnoService.buscarPorId(1) == null);
    }
}