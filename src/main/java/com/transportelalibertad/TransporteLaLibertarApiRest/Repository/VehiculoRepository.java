package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
