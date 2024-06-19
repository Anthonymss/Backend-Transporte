package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.CompraProducto;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.CompraProductoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.CompraRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.CompraProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompraProductoServiceImpl implements CompraProductoService {
    @Autowired
    private CompraProductoRepository compraProductoRepository;

    @Override
    public List<CompraProducto> findAll() {
        return compraProductoRepository.findAll();
    }

    @Override
    public Optional<CompraProducto> findById(Long id) {
        return this.compraProductoRepository.findById(id);
    }

    @Override
    public CompraProducto save(CompraProducto producto) {
        return this.compraProductoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        this.compraProductoRepository.deleteById(id);
    }
}
