package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    public List<Compra> findAll();
    public Optional<Compra> findById(Long id) ;
    public Compra save(Compra producto) ;
    public void deleteById(Long id) ;
}
