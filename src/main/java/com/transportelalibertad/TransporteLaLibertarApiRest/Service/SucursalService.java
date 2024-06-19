package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll();
    Sucursal findById(Long id);
    Sucursal save(Sucursal sucursal);
    void deleteById(Long id);
}
