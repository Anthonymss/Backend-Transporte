package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Almacen;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Producto;

import java.util.List;
import java.util.Optional;

public interface AlmacenService {
    List<Almacen> findAll();
    Optional<Almacen> findById(Long id);
    Almacen save(Almacen almacen);
    void deleteById(Long id);
    public Producto addProductoToAlmacen(Long almacenId, Producto producto);
}
