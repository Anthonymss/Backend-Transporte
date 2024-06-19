package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Conductor;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.ConductorRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.ConductorService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ConductorServiceImpl implements ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;
    @Override
    public List<Conductor> findAll() {
        return this.conductorRepository.findAll();
    }

    @Override
    public Conductor findById(Long id) {
        return this.conductorRepository.findById(id).orElse(null);

    }

    @Override
    public Conductor save(Conductor conductor) {
        return this.conductorRepository.save(conductor);
    }

    @Override
    public void deleteById(Long id) {
        this.conductorRepository.deleteById(id);
    }
}
