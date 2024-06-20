package com.transportelalibertad.TransporteLaLibertarApiRest.Controller;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.*;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.*;
import com.transportelalibertad.TransporteLaLibertarApiRest.mail.CorreoRequest;
import com.transportelalibertad.TransporteLaLibertarApiRest.mail.enviar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private TareaService tareaService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VehiculoService vehiculoService;


    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private enviar enviarmail;
    @PostMapping("/CrearOrdenTrabajo")
    public ResponseEntity<?> createOrdenTrabajo(@RequestBody OrdenTrabajo ordenTrabajo) {
        try {
            OrdenTrabajo nuevaOrdenTrabajo = ordenTrabajoService.save(ordenTrabajo);
            List<Usuario> lista = usuarioService.findAll();
            List<Usuario> usuariosGmail = lista.stream()
                    .filter(usuario -> usuario.getCorreoElectronico() != null && usuario.getCorreoElectronico().endsWith("@gmail.com"))
                    .collect(Collectors.toList());
            System.out.println(usuariosGmail);
            CorreoRequest correo = new CorreoRequest();
            correo.setAsunto("Nueva Orden de trabajo");

            for (Usuario usuario :usuariosGmail) {
                correo.setDestinatario(usuario.getCorreoElectronico());
                System.out.println(usuario.getCorreoElectronico());
                String contenido = "<!DOCTYPE html>\n" +
                        "<html lang=\"es\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Orden de Trabajo</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            font-family: Arial, sans-serif;\n" +
                        "            line-height: 1.6;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            max-width: 600px;\n" +
                        "            margin: 20px auto;\n" +
                        "            padding: 20px;\n" +
                        "            border: 1px solid #ccc;\n" +
                        "            border-radius: 5px;\n" +
                        "            background-color: #f9f9f9;\n" +
                        "        }\n" +
                        "        h1 {\n" +
                        "            color: #333;\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        p {\n" +
                        "            color: #666;\n" +
                        "        }\n" +
                        "        .details {\n" +
                        "            margin-top: 20px;\n" +
                        "        }\n" +
                        "        .details table {\n" +
                        "            width: 100%;\n" +
                        "            border-collapse: collapse;\n" +
                        "            margin-top: 10px;\n" +
                        "        }\n" +
                        "        .details th, .details td {\n" +
                        "            border: 1px solid #ccc;\n" +
                        "            padding: 8px;\n" +
                        "            text-align: left;\n" +
                        "        }\n" +
                        "        .details th {\n" +
                        "            background-color: #f2f2f2;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <h1>++</h1>\n" +
                        "        <p>Estimado/a " + usuario.getNombre() + " " + usuario.getApellido() + "</p>\n" +
                        "        <p>Le informamos que se ha generado la siguiente orden de trabajo:</p>\n" +
                        "        <div class=\"details\">\n" +
                        "            <table>\n" +
                        "                <tr>\n" +
                        "                    <th>Número de Orden:</th>\n" +
                        "                    <td>" + nuevaOrdenTrabajo.getId() + "</td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                    <th>Fecha de Creación:</th>\n" +
                        "                    <td>" + nuevaOrdenTrabajo.getFechaCreacion() + "</td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                    <th>Descripción:</th>\n" +
                        "                    <td>" + nuevaOrdenTrabajo.getDescripcion() + "</td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                    <th>Prioridad:</th>\n" +
                        "                    <td>" + nuevaOrdenTrabajo.getEstado() + "</td>\n" +
                        "                </tr>\n" +
                        "            </table>\n" +
                        "        </div>\n" +
                        "        <p>Por favor, no dude en contactarnos si tiene alguna pregunta o necesita más información.</p>\n" +
                        "        <p>Saludos cordiales,</p>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
                correo.setContenido(contenido);

                enviarmail.enviarmail(correo);
            }

            return new ResponseEntity<>(nuevaOrdenTrabajo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de trabajo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/Creartareas")
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        if (tarea.getTecnico() != null && tarea.getTecnico().getId() != null) {
            Usuario tecnico = usuarioService.findById(tarea.getTecnico().getId()).orElseThrow(null);
            tarea.setTecnico(tecnico);
        }

        if (tarea.getOrdenTrabajo() != null && tarea.getOrdenTrabajo().getId() != null) {
            Optional<OrdenTrabajo> ordenTrabajo = ordenTrabajoService.findById(tarea.getOrdenTrabajo().getId());
            if (ordenTrabajo.isPresent()) {
                tarea.setOrdenTrabajo(ordenTrabajo.get());
            }else {
                return ResponseEntity.notFound().build();
            }

        }

        Tarea newTarea = tareaService.save(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTarea);
    }

    // Gestión de Usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setApellido(usuarioDetails.getApellido());
                usuario.setCorreoElectronico(usuarioDetails.getCorreoElectronico());
                usuario.setContrasena(usuarioDetails.getContrasena());
                usuario.setImagen(usuarioDetails.getImagen());
                usuario.setSucursal(usuarioDetails.getSucursal());
                usuario.setRoles(usuarioDetails.getRoles());
            Usuario updatedUsuario = usuarioService.save(usuario);
            return new ResponseEntity<>( updatedUsuario,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro un usuario con el ID: "+id,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (usuarioOptional.isPresent()) {
            try {
                usuarioService.deleteById(usuarioOptional.get().getId());
                return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("No se puede eliminar el usuario con ID " + id + " porque tiene registros relacionados");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al intentar eliminar el usuario con ID " + id);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Gestión de Flota

    @GetMapping("/vehiculos")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.findAll();
        if (vehiculos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        Optional<Vehiculo> vehiculoOpt = vehiculoService.findById(id);
        if (vehiculoOpt.isPresent()) {
            return new ResponseEntity<>(vehiculoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehiculos")
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo);
            return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        Optional<Vehiculo> vehiculoOpt = vehiculoService.findById(id);
        if (vehiculoOpt.isPresent()) {
            vehiculo.setId(id);
            return new ResponseEntity<>(vehiculoService.save(vehiculo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vehiculos/{id}")
    public ResponseEntity<HttpStatus> deleteVehiculo(@PathVariable Long id) {
        try {
            vehiculoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Gestión de Sucursales (si es necesario)
    @GetMapping("/sucursales")
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }

    @GetMapping("/sucursales/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal != null) {
            return new ResponseEntity<>(sucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/sucursales/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal existingSucursal = sucursalService.findById(id);
        if (existingSucursal != null) {
            sucursal.setId(id);
            Sucursal updatedSucursal = sucursalService.save(sucursal);
            return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
