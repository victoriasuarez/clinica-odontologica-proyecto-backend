package com.dh.clinica.login;

import com.dh.clinica.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUsuarioService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUsuario> usuarioBuscado = userRepository.findByEmail(email);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }
        else {
            throw new UsernameNotFoundException("Email de usuario no encontrado");
        }
    }
}
