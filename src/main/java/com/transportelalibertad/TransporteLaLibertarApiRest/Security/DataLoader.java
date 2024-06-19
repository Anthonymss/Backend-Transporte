package com.transportelalibertad.TransporteLaLibertarApiRest.Security;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Rol;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Sucursal;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Usuario;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.RolRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.SucursalRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class DataLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData(UsuarioRepository usuarioRepository) {
        return args -> {
            Optional<Usuario> usuario1=usuarioRepository.findById(1L);
            if (usuario1.isPresent()) {
                Usuario usuario = usuario1.get();
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                usuarioRepository.save(usuario);
            }
            Optional<Usuario> usuario2=usuarioRepository.findById(2L);
            if (usuario2.isPresent()) {
                Usuario usuario = usuario2.get();
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                usuarioRepository.save(usuario);
            }
            Optional<Usuario> usuario3=usuarioRepository.findById(3L);
            if (usuario3.isPresent()) {
                Usuario usuario = usuario3.get();
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                usuarioRepository.save(usuario);
            }
        };
    }
}
