package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.ReporteFallo;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.ReporteFalloRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.ReporteFalloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteFalloServiceImpl implements ReporteFalloService {

    @Autowired
    private ReporteFalloRepository reporteFalloRepository;

    @Override
    public ReporteFallo save(ReporteFallo reporteFallo) {
        return reporteFalloRepository.save(reporteFallo);
    }

    @Override
    public List<ReporteFallo> findAll() {
        return reporteFalloRepository.findAll();
    }

    @Override
    public List<ReporteFallo> findByEstado(String estado) {
        return reporteFalloRepository.findByEstado(estado);
    }

    @Override
    public ReporteFallo updateEstado(Long reporteId, String estado) {
        ReporteFallo reporte = reporteFalloRepository.findById(reporteId).orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        reporte.setEstado(estado);
        return reporteFalloRepository.save(reporte);
    }

    @Override
    public List<ReporteFallo> findByIdTecnico(Long Id) {
        return reporteFalloRepository.findByTecnicoId(Id);
    }
}