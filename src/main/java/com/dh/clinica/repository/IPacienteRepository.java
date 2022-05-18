package com.dh.clinica.repository;

import com.dh.clinica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// jpa recibe entity y tipo de id
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
