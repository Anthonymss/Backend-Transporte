package com.transportelalibertad.TransporteLaLibertarApiRest.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolicitudRepuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado; // Ej: PENDIENTE, APROBADA, RECHAZADA

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date fechaCreacion;

    @ManyToMany
    @JsonIgnore
    @JoinTable(

            name = "solicitud_repuesto_producto",
            joinColumns = @JoinColumn(name = "solicitud_repuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos;
    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
        estado = "Pendiente";
    }
}