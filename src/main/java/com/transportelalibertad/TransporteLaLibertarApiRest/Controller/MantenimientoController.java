package com.transportelalibertad.TransporteLaLibertarApiRest.Controller;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.*;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/mantenimiento")
@RestController
public class MantenimientoController {

    @Autowired
    private TareaService tareaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SolicitudRepuestoService solicitudRepuestoService;

    @Autowired
    private ReporteFalloService reporteFalloService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @GetMapping("/OrdenTrabajogetAll")
    public ResponseEntity<?> getAllOrdenTrabajo() {
        try {
            return new ResponseEntity<>(ordenTrabajoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las ordenes de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/OrdenTrabajo/{id}")
    public ResponseEntity<?> getOrdenTrabajoById(@PathVariable Long id) {
        try {
            Optional<OrdenTrabajo> ordenTrabajoOpt = ordenTrabajoService.findById(id);
            if (ordenTrabajoOpt.isPresent()) {
                return new ResponseEntity<>(ordenTrabajoOpt.get().getTareas(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de trabajo no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la orden de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/OrdenTrabajo/{id}/actualizar/{estado}")
    public ResponseEntity<?> iniciarOrdenTrabajo(@PathVariable Long id,@PathVariable String estado) {
        try {
            Optional<OrdenTrabajo> ordenTrabajoOpt = ordenTrabajoService.findById(id);
            if (ordenTrabajoOpt.isPresent()) {
                OrdenTrabajo ordenTrabajo = ordenTrabajoOpt.get();
                if ((estado.equalsIgnoreCase("Finalizado"))) {
                    ordenTrabajo.finalizar();
                } else {
                    ordenTrabajo.setEstado(estado);
                }

                ordenTrabajoService.save(ordenTrabajo);
                return new ResponseEntity<>(ordenTrabajo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de trabajo no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al iniciar la orden de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Gestión de Tareas

    @GetMapping("/tareas")
    public ResponseEntity<List<Tarea>> getAllTareas() {
        return ResponseEntity.ok(tareaService.findAll());
    }

    @GetMapping("/tareas/tecnico/{tecnicoId}")
    public ResponseEntity<List<Tarea>> getTareasByTecnico(@PathVariable Long tecnicoId) {
        return ResponseEntity.ok(tareaService.findByTecnicoId(tecnicoId));
    }

    @PutMapping("/tareas/{tareaId}/estado")
    public ResponseEntity<Tarea> updateTareaEstado(@PathVariable Long tareaId, @RequestParam String estado) {
        Tarea updatedTarea = tareaService.updateEstado(tareaId, estado);
        return ResponseEntity.ok(updatedTarea);
    }
    @PutMapping("/{tareaId}/asignar")
    public ResponseEntity<Tarea> asignarTecnico(@PathVariable Long tareaId, @RequestBody Map<String, Long> request) {
        Long tecnicoId = request.get("tecnicoId");
        Tarea tarea = tareaService.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        Usuario tecnico = usuarioService.findById(tecnicoId)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
        tarea.setTecnico(tecnico);
        Tarea updatedTarea = tareaService.save(tarea);
        return ResponseEntity.ok(updatedTarea);
    }


    // Gestión de solicitudes de repuestos

    @PostMapping("/solicitudes-repuestos")
    public ResponseEntity<SolicitudRepuesto> createSolicitudRepuesto(@RequestBody SolicitudRepuesto solicitudRepuesto) {
        SolicitudRepuesto newSolicitud = solicitudRepuestoService.save(solicitudRepuesto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSolicitud);
    }

    @GetMapping("/solicitudes-repuestos")
    public ResponseEntity<List<SolicitudRepuesto>> getAllSolicitudesRepuestos() {
        return ResponseEntity.ok(solicitudRepuestoService.findAll());
    }

    @GetMapping("/solicitudes-repuestos/tecnico/{tecnicoId}")
    public ResponseEntity<List<SolicitudRepuesto>> getSolicitudesRepuestosByTecnico(@PathVariable Long tecnicoId) {
        return ResponseEntity.ok(solicitudRepuestoService.findByTecnicoId(tecnicoId));
    }

    @PutMapping("/solicitudes-repuestos/{solicitudId}/estado")
    public ResponseEntity<SolicitudRepuesto> updateSolicitudRepuestoEstado(@PathVariable Long solicitudId, @RequestParam String estado) {
        SolicitudRepuesto updatedSolicitud = solicitudRepuestoService.updateEstado(solicitudId, estado);
        return ResponseEntity.ok(updatedSolicitud);
    }
    @DeleteMapping("/solicitudes-repuestos/{Id}")
    public void eliminarporid(@PathVariable Long Id) {
        solicitudRepuestoService.deleteById(Id);
    }

    // Gestión de Reportes de Fallos

    @PostMapping("/reportes-fallos")
    public ResponseEntity<ReporteFallo> createReporteFallo(@RequestBody ReporteFallo reporteFallo) {
        ReporteFallo newReporte = reporteFalloService.save(reporteFallo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReporte);
    }

    @GetMapping("/reportes-fallos")
    public ResponseEntity<List<ReporteFallo>> getAllReportesFallos() {
        return ResponseEntity.ok(reporteFalloService.findAll());
    }

    @GetMapping("/reportes-fallos/estado/{estado}")
    public ResponseEntity<List<ReporteFallo>> getReportesFallosByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reporteFalloService.findByEstado(estado));
    }

    @PutMapping("/reportes-fallos/{reporteId}/estado")
    public ResponseEntity<ReporteFallo> updateReporteFalloEstado(@PathVariable Long reporteId, @RequestParam String estado) {
        ReporteFallo updatedReporte = reporteFalloService.updateEstado(reporteId, estado);
        return ResponseEntity.ok(updatedReporte);
    }
}