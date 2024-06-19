package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Vehiculo;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.VehiculoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.VehiculoService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Override
    public List<Vehiculo> findAll() {
        return this.vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return this.vehiculoRepository.findById(id);
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return this.vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        this.vehiculoRepository.deleteById(id);
    }
}
