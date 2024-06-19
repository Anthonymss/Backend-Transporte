package com.transportelalibertad.TransporteLaLibertarApiRest.Security;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Usuario;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByCorreoElectronico(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    return  new CustomUserDetails(usuario);
}

}
