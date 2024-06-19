package com.transportelalibertad.TransporteLaLibertarApiRest.Repository;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
