package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.OrdenTrabajo;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.OrdenTrabajoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {
    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;
    @Override
    public List<OrdenTrabajo> findAll() {
        return ordenTrabajoRepository.findAll();
    }

    public Optional<OrdenTrabajo> findById(Long id) {
        return ordenTrabajoRepository.findById(id);
    }

    @Override
    public OrdenTrabajo save(OrdenTrabajo ordenTrabajo) {
        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    @Override
    public void deleteById(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
