package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Almacen;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Producto;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.AlmacenRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.ProductoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.AlmacenService;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AlmacenServiceImpl implements AlmacenService {
    @Autowired
    private AlmacenRepository almacenRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Almacen> findAll() {
        return this.almacenRepository.findAll();
    }

    @Override
    public Optional<Almacen> findById(Long id) {
        return this.almacenRepository.findById(id);
    }

    @Override
    public Almacen save(Almacen almacen) {
        return this.almacenRepository.save(almacen);
    }

    @Override
    public void deleteById(Long id) {
        this.almacenRepository.deleteById(id);
    }

    @Override
    public Producto addProductoToAlmacen(Long almacenId, Producto producto) {
        Optional<Almacen> almacenOpt = almacenRepository.findById(almacenId);
        if (almacenOpt.isPresent()) {
            Almacen almacen = almacenOpt.get();
            producto = productoRepository.save(producto);
            almacen.getProductos().add(producto);
            almacenRepository.save(almacen);
            return producto;
        } else {
            throw new RuntimeException("Almacen no encontrado");
        }
    }
}
