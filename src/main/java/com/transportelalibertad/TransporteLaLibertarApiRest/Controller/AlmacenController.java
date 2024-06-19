package com.transportelalibertad.TransporteLaLibertarApiRest.Controller;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.*;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/almacen")
public class AlmacenController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private SolicitudRepuestoService solicitudRepuestoService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraProductoService compraProductoService;
    @Autowired
    private AlmacenService almacenService;
    @GetMapping("/productos-almacen/{id}")
    public ResponseEntity<?> getProductosByIdAlmacen(@PathVariable Long id) {
        Optional<Almacen> almacenOptional=almacenService.findById(id);
        if(almacenOptional.isPresent()){
            return new ResponseEntity<>(almacenOptional.get().getProductos(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Id de ALmacen incorrecto",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/productos/{id}")
    public ResponseEntity<?> addProductoToAlmacen(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto nuevoProducto = almacenService.addProductoToAlmacen(id, producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agregar el producto al almacén: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoData = productoService.findById(id);
        if (productoData.isPresent()) {
            producto.setId(id);
            Producto updatedProducto = productoService.save(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Proveedores
    @GetMapping("/proveedores")
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return proveedores.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.findById(id);
        return proveedor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/proveedores")
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor nuevoProveedor = proveedorService.save(proveedor);
            return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos para gestionar Solicitudes de Repuestos
    @GetMapping("/solicitudesRepuestos")
    public ResponseEntity<List<SolicitudRepuesto>> getAllSolicitudesRepuestos() {
        List<SolicitudRepuesto> solicitudesRepuestos = solicitudRepuestoService.findAll();

        return solicitudesRepuestos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(solicitudesRepuestos, HttpStatus.OK);
    }

    @GetMapping("/solicitudesRepuestos/{id}")
    public ResponseEntity<SolicitudRepuesto> getSolicitudRepuestoById(@PathVariable Long id) {
        Optional<SolicitudRepuesto> solicitudRepuesto = solicitudRepuestoService.findById(id);
        return solicitudRepuesto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/solicitudesRepuestos")
    public ResponseEntity<SolicitudRepuesto> createSolicitudRepuesto(@RequestBody SolicitudRepuesto solicitudRepuesto) {
        try {
            SolicitudRepuesto nuevaSolicitudRepuesto = solicitudRepuestoService.save(solicitudRepuesto);
            return new ResponseEntity<>(nuevaSolicitudRepuesto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/solicitudesRepuestos/{id}")
    public ResponseEntity<SolicitudRepuesto> updateSolicitudRepuesto(@PathVariable Long id, @RequestBody SolicitudRepuesto solicitudRepuesto) {
        Optional<SolicitudRepuesto> solicitudRepuestoData = solicitudRepuestoService.findById(id);
        if (solicitudRepuestoData.isPresent()) {
            solicitudRepuesto.setId(id);
            SolicitudRepuesto updatedSolicitudRepuesto = solicitudRepuestoService.save(solicitudRepuesto);
            return new ResponseEntity<>(updatedSolicitudRepuesto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/solicitudesRepuestos/{id}")
    public ResponseEntity<?> deleteSolicitudRepuesto(@PathVariable Long id) {
        try {
            Optional<SolicitudRepuesto> soli=solicitudRepuestoService.findById(id);
            if (soli.isPresent()) {
                solicitudRepuestoService.deleteById(id);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Eliminado",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    // Métodos para gestionar Compras
    @GetMapping("/compras")
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAll();
        return compras.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/compras/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        Optional<Compra> compra = compraService.findById(id);
        return compra.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/compras")
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        try {
            Compra nuevaCompra = compraService.save(compra);
            return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/compras/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @RequestBody Compra compra) {
        Optional<Compra> compraData = compraService.findById(id);
        if (compraData.isPresent()) {
            Compra updatedCompra = compraService.save(compra);
            return new ResponseEntity<>(updatedCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/compras/{id}")
    public ResponseEntity<HttpStatus> deleteCompra(@PathVariable Long id) {
        try {
            compraService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos para gestionar CompraProducto
    @GetMapping("/compraProductos")
    public ResponseEntity<List<CompraProducto>> getAllCompraProductos() {
        List<CompraProducto> compraProductos = compraProductoService.findAll();
        return compraProductos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(compraProductos, HttpStatus.OK);
    }

    @GetMapping("/compraProductos/{id}")
    public ResponseEntity<CompraProducto> getCompraProductoById(@PathVariable Long id) {
        Optional<CompraProducto> compraProducto = compraProductoService.findById(id);
        return compraProducto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/compraProductos")
    public ResponseEntity<CompraProducto> createCompraProducto(@RequestBody CompraProducto compraProducto) {
        try {
            CompraProducto nuevoCompraProducto = compraProductoService.save(compraProducto);
            return new ResponseEntity<>(nuevoCompraProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/compraProductos/{id}")
    public ResponseEntity<CompraProducto> updateCompraProducto(@PathVariable Long id, @RequestBody CompraProducto compraProducto) {
        Optional<CompraProducto> compraProductoData = compraProductoService.findById(id);
        if (compraProductoData.isPresent()) {
            CompraProducto updatedCompraProducto = compraProductoService.save(compraProducto);
            return new ResponseEntity<>(updatedCompraProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/compraProductos/{id}")
    public ResponseEntity<HttpStatus> deleteCompraProducto(@PathVariable Long id) {
        try {
            compraProductoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
