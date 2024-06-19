package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.CompraProducto;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.CompraProductoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.CompraRepository;

import java.util.List;
import java.util.Optional;

public interface CompraProductoService {
    public List<CompraProducto> findAll();
    public Optional<CompraProducto> findById(Long id) ;
    public CompraProducto save(CompraProducto producto) ;
    public void deleteById(Long id) ;
}
