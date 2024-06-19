package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.SolicitudRepuesto;

import java.util.List;
import java.util.Optional;

public interface SolicitudRepuestoService {
    SolicitudRepuesto save(SolicitudRepuesto solicitudRepuesto);
    List<SolicitudRepuesto> findAll();
    List<SolicitudRepuesto> findByTecnicoId(Long tecnicoId);
    void deleteById(Long id);

    Optional<SolicitudRepuesto> findById(Long id);
    public SolicitudRepuesto addProductoToSolicitud(SolicitudRepuesto solicitud) ;

    public SolicitudRepuesto updateEstado(Long id, String estado);
}