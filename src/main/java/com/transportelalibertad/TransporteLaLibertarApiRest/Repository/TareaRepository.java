package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEstado(String estado);
    List<Tarea> findByTecnicoId(Long tecnicoId);
}