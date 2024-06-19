package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Rol;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.RolRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.RolService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol save(Rol rol) {
        return this.rolRepository.save(rol);
    }

    @Override
    public void deleteById(Long id) {
        this.rolRepository.deleteById(id);
    }

    @Override
    public Rol findByNombre(String nombre) {
        return this.rolRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return this.rolRepository.findById(id);
    }
}
