package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    List<Proveedor> findAll();
    Optional<Proveedor> findById(Long id);
    Proveedor save(Proveedor proveedor);
    void deleteById(Long id);
}
