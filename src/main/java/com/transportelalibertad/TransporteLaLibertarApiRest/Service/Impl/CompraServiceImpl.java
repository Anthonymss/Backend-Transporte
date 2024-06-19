package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Compra;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.CompraRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompraServiceImpl implements CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Override
    public List<Compra> findAll() {
        return this.compraRepository.findAll();
    }

    @Override
    public Optional<Compra> findById(Long id) {
        return this.compraRepository.findById(id);
    }

    @Override
    public Compra save(Compra producto) {
        return this.compraRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        this.compraRepository.deleteById(id);
    }
}
