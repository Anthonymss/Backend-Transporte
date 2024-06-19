package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    List<Vehiculo> findAll();
    Optional<Vehiculo> findById(Long id);
    Vehiculo save(Vehiculo vehiculo);
    void deleteById(Long id);
}
