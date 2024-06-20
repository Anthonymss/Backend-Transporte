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
    private ProductoService productoService;
    @Autowired
    private ReporteFalloService reporteFalloService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private AlmacenService almacenService;
    @GetMapping("/OrdenTrabajogetAll")
    public ResponseEntity<?> getAllOrdenTrabajo() {
        try {
            return new ResponseEntity<>(ordenTrabajoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las ordenes de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //solicitud


    //solicitud


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
    @GetMapping("/productos-almacen/{id}")
    public ResponseEntity<?> getProductosByIdAlmacen(@PathVariable Long id) {
        Optional<Almacen> almacenOptional=almacenService.findById(id);
        if(almacenOptional.isPresent()){
            return new ResponseEntity<>(almacenOptional.get().getProductos(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id de ALmacen incorrecto",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listarProductosBySolicitud/{id}")
    public ResponseEntity<?> getAllProductosBySolicitud(@PathVariable Long id) {
        Optional<SolicitudRepuesto> solicitudRepuestoOptional=solicitudRepuestoService.findById(id);
        if(solicitudRepuestoOptional.isPresent()){
            return new ResponseEntity<>(solicitudRepuestoOptional.get().getProductos(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id de solicitud incorrecto",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/solicitudes-repuestos")
    public ResponseEntity<SolicitudRepuesto> createSolicitudRepuesto(@RequestBody SolicitudRepuesto solicitudRepuesto) {
        try {
            SolicitudRepuesto nuevaSolicitudRepuesto = solicitudRepuestoService.save(solicitudRepuesto);
            return new ResponseEntity<>(nuevaSolicitudRepuesto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/agregarProducto/{solicitudId}/productos/{productoId}")
    public ResponseEntity<?> addProductoToSolicitud(@PathVariable Long solicitudId, @PathVariable Long productoId) {
        Optional<SolicitudRepuesto> solicitudOpt = solicitudRepuestoService.findById(solicitudId);
        Optional<Producto> productoOpt = productoService.findById(productoId);

        if (solicitudOpt.isPresent() && productoOpt.isPresent()) {
            SolicitudRepuesto solicitud = solicitudOpt.get();
            Producto producto = productoOpt.get();
            solicitud.getProductos().add(producto);
            solicitudRepuestoService.save(solicitud);
            return new ResponseEntity<>(solicitud.getProductos(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al agregar el producto a la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @GetMapping("/listarReportes/{id}")
    public ResponseEntity<?> getReportesFallosByIdTecnico(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity<>(reporteFalloService.findByIdTecnico(id),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id de tecnico incorrecto",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/reportes-fallos/{reporteId}/estado")
    public ResponseEntity<ReporteFallo> updateReporteFalloEstado(@PathVariable Long reporteId, @RequestParam String estado) {
        ReporteFallo updatedReporte = reporteFalloService.updateEstado(reporteId, estado);
        return ResponseEntity.ok(updatedReporte);
    }
}