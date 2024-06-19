package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Rol;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Usuario;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.RolRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.UsuarioRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void assignRoleToUser(Long userId, Long roleId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
        Optional<Rol> rolOptional = rolRepository.findById(roleId);

        if (usuarioOptional.isPresent() && rolOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Rol rol = rolOptional.get();

            usuario.getRoles().add(rol);
            usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario o Rol no encontrado");
        }
    }

    @Override
    public Optional<Usuario> findByCorreoElectronico(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }


}
