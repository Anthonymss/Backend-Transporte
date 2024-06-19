package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.ReporteFallo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReporteFalloRepository extends JpaRepository<ReporteFallo, Long> {
    List<ReporteFallo> findByEstado(String estado);
}