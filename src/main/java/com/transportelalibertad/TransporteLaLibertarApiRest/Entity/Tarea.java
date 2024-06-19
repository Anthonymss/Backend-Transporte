package com.transportelalibertad.TransporteLaLibertarApiRest.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    private String estado; //  PENDIENTE, EN_PROGRESO, COMPLETADA

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;
    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id")
    private OrdenTrabajo ordenTrabajo;
    @PrePersist
    protected void onCreate() {
        fechaInicio = null;
        estado = "Pendiente";
    }

}