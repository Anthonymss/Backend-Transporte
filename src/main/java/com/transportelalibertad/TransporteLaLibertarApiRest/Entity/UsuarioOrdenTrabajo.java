package com.transportelalibertad.TransporteLaLibertarApiRest.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UsuarioOrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id")
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}