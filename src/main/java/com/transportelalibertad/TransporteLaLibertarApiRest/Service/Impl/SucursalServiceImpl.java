package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Sucursal;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.SucursalRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.SucursalService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return this.sucursalRepository.findById(id).orElse(null);
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }
}
