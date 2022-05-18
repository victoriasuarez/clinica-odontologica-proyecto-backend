package com.dh.clinica.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargaDeDatos implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Para encriptar la contraseña
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contraseniaAdmin = passwordEncoder.encode("password");
        String contraseniaUser = passwordEncoder.encode("password1");

        userRepository.save(new AppUsuario("Victoria", "vicoosuarezz", "victoriasuarez49@gmail.com", contraseniaAdmin, AppUsuarioRoles.ROLE_ADMIN));
        userRepository.save(new AppUsuario("Victoria1", "vicoosuarezz1", "victoria1suarez49@gmail.com", contraseniaUser, AppUsuarioRoles.ROLE_USER));


    }
}
