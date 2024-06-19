package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> findAll();
    public Optional<Producto> findById(Long id) ;
    public Producto save(Producto producto) ;
    public void deleteById(Long id) ;
}
