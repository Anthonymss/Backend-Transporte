package com.transportelalibertad.TransporteLaLibertarApiRest.Service;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Tarea;
import java.util.List;
import java.util.Optional;

public interface TareaService {
    Tarea save(Tarea tarea);
    List<Tarea> findAll();
    List<Tarea> findByTecnicoId(Long tecnicoId);
    List<Tarea> findByEstado(String estado);
    Tarea updateEstado(Long tareaId, String estado);
    Optional<Tarea> findById(Long id);
}