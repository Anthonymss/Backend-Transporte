package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.OrdenTrabajo;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface OrdenTrabajoService {
    List<OrdenTrabajo> findAll();
    OrdenTrabajo save(OrdenTrabajo ordenTrabajo);
    void deleteById(Long id);
    Optional<OrdenTrabajo> findById(Long id);
}
