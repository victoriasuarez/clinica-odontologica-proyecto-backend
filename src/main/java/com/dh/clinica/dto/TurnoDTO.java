package com.dh.clinica.dto;

import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.entities.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TurnoDTO {

    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;
    private LocalTime hora;

    public TurnoDTO() {
    }

    public TurnoDTO(Turno turno) {
        this.id = turno.getId();
        this.paciente = turno.getPaciente();
        this.odontologo = turno.getOdontologo();
        this.fecha = turno.getFecha();
        this.hora = turno.getHora();
    }

    public TurnoDTO(Integer id, Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public TurnoDTO(Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                ", hora=" + hora +
                '}';
    }
}
