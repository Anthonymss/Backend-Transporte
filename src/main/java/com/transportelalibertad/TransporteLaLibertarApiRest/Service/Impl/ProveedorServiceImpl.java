package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Proveedor;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.ProveedorRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.ProveedorService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Override
    public List<Proveedor> findAll() {
        return this.proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return this.proveedorRepository.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return this.proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        this.proveedorRepository.deleteById(id);
    }
}
