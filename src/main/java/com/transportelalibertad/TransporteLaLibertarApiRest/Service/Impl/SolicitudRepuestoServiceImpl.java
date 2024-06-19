package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Producto;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.SolicitudRepuesto;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.ProductoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.SolicitudRepuestoRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.SolicitudRepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudRepuestoServiceImpl implements SolicitudRepuestoService {

    @Autowired
    private SolicitudRepuestoRepository solicitudRepuestoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public SolicitudRepuesto save(SolicitudRepuesto solicitudRepuesto) {
        return solicitudRepuestoRepository.save(solicitudRepuesto);
    }

    @Override
    public List<SolicitudRepuesto> findAll() {
        return solicitudRepuestoRepository.findAll();
    }

    @Override
    public List<SolicitudRepuesto> findByTecnicoId(Long tecnicoId) {
        return solicitudRepuestoRepository.findByTecnicoId(tecnicoId);
    }

    @Override
    public SolicitudRepuesto updateEstado(Long id, String estado) {
        Optional<SolicitudRepuesto> solicitudOpt = solicitudRepuestoRepository.findById(id);
        if (solicitudOpt.isPresent()) {
            SolicitudRepuesto solicitud = solicitudOpt.get();
            solicitud.setEstado(estado);
            return solicitudRepuestoRepository.save(solicitud);
        } else {
            throw new RuntimeException("Solicitud no encontrada");
        }
    }

    @Override
    public void deleteById(Long id) {
        this.solicitudRepuestoRepository.deleteById(id);
    }

    @Override
    public Optional<SolicitudRepuesto> findById(Long id) {
        return this.solicitudRepuestoRepository.findById(id);
    }

    @Override
    public SolicitudRepuesto addProductoToSolicitud(SolicitudRepuesto solicitud) {

            return solicitudRepuestoRepository.save(solicitud);

    }
}
