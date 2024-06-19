package com.transportelalibertad.TransporteLaLibertarApiRest.Service;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.ReporteFallo;

import java.util.List;

public interface ReporteFalloService {
    ReporteFallo save(ReporteFallo reporteFallo);
    List<ReporteFallo> findAll();
    List<ReporteFallo> findByEstado(String estado);
    ReporteFallo updateEstado(Long reporteId, String estado);
}