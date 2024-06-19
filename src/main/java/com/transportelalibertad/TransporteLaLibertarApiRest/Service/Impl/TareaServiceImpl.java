package com.transportelalibertad.TransporteLaLibertarApiRest.Service.Impl;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Tarea;
import com.transportelalibertad.TransporteLaLibertarApiRest.Repository.TareaRepository;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }
    @Override
    public List<Tarea> findByTecnicoId(Long tecnicoId) {
        return tareaRepository.findByTecnicoId(tecnicoId);
    }
    @Override
    public List<Tarea> findByEstado(String estado) {
        return tareaRepository.findByEstado(estado);
    }

    @Override
    public Tarea updateEstado(Long tareaId, String estado) {
        Tarea tarea = tareaRepository.findById(tareaId).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setEstado(estado);
        return tareaRepository.save(tarea);
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id);
    }
}