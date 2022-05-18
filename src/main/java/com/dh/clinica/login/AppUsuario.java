package com.dh.clinica.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class AppUsuario implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String Password;
    @Enumerated(EnumType.STRING)
    private AppUsuarioRoles appUsuarioRoles;

    public AppUsuario(){

    }

    public AppUsuario(String nombre, String userName, String email, String password, AppUsuarioRoles appUsuarioRoles) {
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        Password = password;
        this.appUsuarioRoles = appUsuarioRoles;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUsuarioRoles.name());
        return Collections.singletonList(grantedAuthority);
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public AppUsuarioRoles getAppUsuarioRoles() {
        return appUsuarioRoles;
    }

    public void setAppUsuarioRoles(AppUsuarioRoles appUsuarioRoles) {
        this.appUsuarioRoles = appUsuarioRoles;
    }
}
