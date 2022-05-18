package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.OdontologoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OdontologoServiceTest {

    OdontologoService odontologoService;

    @Test
    public void buscarTodosLosOdontologos() {
        List<OdontologoDTO> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);

    }

    @Test
    public void guardarOdontologos() throws BadRequestException {
        OdontologoDTO odontologoDto = odontologoService.guardar(new OdontologoDTO("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologoDto.getId() != null);
    }

    @Test
    public void buscarOdontologPorId() throws ResourceNotFoundException {
        OdontologoDTO odontologoDto = odontologoService.buscarPorId(1);
        Assert.assertTrue(odontologoDto.getId() == 1);
    }

    @Test
    public void actualizarDatosDelOdontologo() throws ResourceNotFoundException {
        OdontologoDTO odontologoDto = odontologoService.buscarPorId(1);
        odontologoDto.setNombre("Juan");
        odontologoDto.setApellido("Ramirez");
        odontologoDto.setMatricula(985418415);
        odontologoService.actualizar(odontologoDto);
        Assert.assertTrue(odontologoService.buscarPorId(1).getNombre().equals("Juan"));
        Assert.assertTrue(odontologoService.buscarPorId(1).getApellido().equals("Ramirez"));
        Assert.assertTrue(odontologoService.buscarPorId(1).getMatricula() == 985418415);
    }

    @Test
    public void eliminarOdontologo() throws ResourceNotFoundException {
        odontologoService.eliminar(1);
        Assert.assertTrue(odontologoService.buscarPorId(1) == null);
    }
}