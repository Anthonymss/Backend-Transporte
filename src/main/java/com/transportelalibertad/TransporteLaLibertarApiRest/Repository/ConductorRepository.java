package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
