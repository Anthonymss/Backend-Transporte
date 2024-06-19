package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.SolicitudRepuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudRepuestoRepository extends JpaRepository<SolicitudRepuesto, Long> {
    @Query("SELECT sr FROM SolicitudRepuesto sr WHERE sr.tecnico.id = :tecnicoId")
    List<SolicitudRepuesto> findByTecnicoId(@Param("tecnicoId") Long tecnicoId);
}