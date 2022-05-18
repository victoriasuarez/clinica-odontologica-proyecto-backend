package com.dh.clinica.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <AppUsuario, Integer> {

    Optional<AppUsuario> findByEmail(String email);

}
