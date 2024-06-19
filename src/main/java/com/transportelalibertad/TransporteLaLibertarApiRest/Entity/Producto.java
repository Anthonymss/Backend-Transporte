package com.transportelalibertad.TransporteLaLibertarApiRest.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidadEnStock;
    private int stockMinimo;

    @JsonIgnore
    @ManyToMany(mappedBy = "productos")
    private Set<SolicitudRepuesto> solicitudesRepuesto;
    @JsonIgnore
    @ManyToMany(mappedBy = "productos")
    private Set<Almacen> almacenes;
}