package com.dh.clinica.service;

import com.dh.clinica.dto.DomicilioDTO;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.DomicilioService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DomicilioServiceTest {

    DomicilioService domicilioService;

    @Test
    public void buscarTodosLosDomicilio() {
        List<DomicilioDTO> domicilioDto = domicilioService.buscarTodos();
        Assert.assertTrue(!domicilioDto.isEmpty());
        Assert.assertTrue(domicilioDto.size() > 0);

    }

    @Test
    public void guardarDomicilio() throws BadRequestException {
        DomicilioDTO domicilioDto = domicilioService.guardar(new DomicilioDTO("General paz", "818", "San Cristobal", "Santa fe"));
        Assert.assertTrue(domicilioDto.getId() != null);
    }

    @Test
    public void buscarDomicilioPorId() throws ResourceNotFoundException {
        DomicilioDTO domicilioDto = domicilioService.buscarPorId(1);
        Assert.assertTrue(domicilioDto.getId() == null);
    }

    @Test
    public void actualizarDomicilio() throws ResourceNotFoundException {
        DomicilioDTO domicilioDto = domicilioService.buscarPorId(1);
        domicilioDto.setCalle("General paz");
        domicilioDto.setNumero("818");
        domicilioDto.setLocalidad("San Cristobal");
        domicilioDto.setProvincia("Santa fe");
        domicilioService.actualizar(domicilioDto);
        Assert.assertTrue(domicilioService.buscarPorId(1).getCalle().equals("General paz"));
        Assert.assertTrue(domicilioService.buscarPorId(1).getNumero().equals("818"));
        Assert.assertTrue(domicilioService.buscarPorId(1).getLocalidad().equals("San Cristobal"));
        Assert.assertTrue(domicilioService.buscarPorId(1).getProvincia().equals("Santa fe"));
    }

    @Test
    public void eliminarDomicilio() throws ResourceNotFoundException {
        domicilioService.eliminar(1);
        Assert.assertTrue(domicilioService.buscarPorId(1) == null);
    }
}