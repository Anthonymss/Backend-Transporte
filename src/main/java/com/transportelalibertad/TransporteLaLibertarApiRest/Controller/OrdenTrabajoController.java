package com.transportelalibertad.TransporteLaLibertarApiRest.Controller;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.OrdenTrabajo;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/ordenTrabajo")
public class OrdenTrabajoController {
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @PostMapping("/CrearOrdenTrabajo")
    public ResponseEntity<?> createOrdenTrabajo(@RequestBody OrdenTrabajo ordenTrabajo) {
        try {
            OrdenTrabajo nuevaOrdenTrabajo = ordenTrabajoService.save(ordenTrabajo);
            return new ResponseEntity<>(nuevaOrdenTrabajo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}/actualizar/{estado}")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdenTrabajoById(@PathVariable Long id) {
        try {
            Optional<OrdenTrabajo> ordenTrabajoOpt = ordenTrabajoService.findById(id);
            if (ordenTrabajoOpt.isPresent()) {
                return new ResponseEntity<>(ordenTrabajoOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de trabajo no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la orden de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrdenTrabajo() {
        try {
            return new ResponseEntity<>(ordenTrabajoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las ordenes de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
