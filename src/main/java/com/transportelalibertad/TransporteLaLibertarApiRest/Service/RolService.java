package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> findAll();
    Rol save(Rol rol);
    void deleteById(Long id);
    Rol findByNombre(String nombre);
    Optional<Rol> findById(Long id);
}
