package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Conductor;

import java.util.List;

public interface ConductorService {
    List<Conductor> findAll();
    Conductor findById(Long id);
    Conductor save(Conductor conductor);
    void deleteById(Long id);
}
