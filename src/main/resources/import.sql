INSERT INTO sucursal ( nombre, direccion, ciudad) VALUES ( 'Sucursal Central', 'Avenida Principal 123', 'Arequipa');
INSERT INTO sucursal (nombre, direccion, ciudad) VALUES ( 'Sucursal Norte', 'Calle Norte 456', 'Puno');
INSERT INTO sucursal ( nombre, direccion, ciudad) VALUES ( 'Sucursal Sur', 'Carrera Sur 789', 'Ciudad Sur');

INSERT INTO proveedor ( nombre, direccion, telefono) VALUES ( 'Proveedor ABC', 'Calle Comercio 123', '123456789');
INSERT INTO proveedor ( nombre, direccion, telefono) VALUES ( 'Proveedor XYZ', 'Avenida Industrial 456', '987654321');
INSERT INTO proveedor ( nombre, direccion, telefono) VALUES ( 'Proveedor 123', 'Boulevard Empresarial 789', '112233445');


INSERT INTO rol ( nombre) VALUES('ADMINISTRADOR');
INSERT INTO rol ( nombre) VALUES( 'MANTENIMIENTO');
INSERT INTO rol ( nombre) VALUES( 'ALMACEN');


INSERT INTO usuario ( nombre, apellido, correo_electronico, contrasena, imagen, sucursal_id) VALUES ( 'Gian Anthony', 'Choque', 'giananthonych@gmail.com', '123', 'noasignado', 1);
INSERT INTO usuario ( nombre, apellido, correo_electronico, contrasena, imagen, sucursal_id) VALUES ( 'Alex', 'Guzman', 'correoprueba@gasmail.com', '123', 'noasignado', 1);
INSERT INTO usuario ( nombre, apellido, correo_electronico, contrasena, imagen, sucursal_id) VALUES ( "Laura", "Salas", 'correopruebalarura@gmmaail.com', '123', 'noasignado', 1);
INSERT INTO usuario ( nombre, apellido, correo_electronico, contrasena, imagen, sucursal_id) VALUES ( 'Maria', 'Gomez', 'maria.gomez@example.com', 'password123', 'imagen2.png', 2);
INSERT INTO usuario ( nombre, apellido, correo_electronico, contrasena, imagen, sucursal_id) VALUES ( 'Carlos', 'Lopez', 'carlos.lopez@example.com', 'password123', 'imagen3.png', 3);

INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2, 3);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (3, 1);

INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (3, 3);


INSERT INTO conductor (id, nombre, apellido, licencia) VALUES (1, 'Pedro', 'Ramirez', 'L123456');
INSERT INTO conductor (id, nombre, apellido, licencia) VALUES (2, 'Ana', 'Martinez', 'L654321');
INSERT INTO conductor (id, nombre, apellido, licencia) VALUES (3, 'Luis', 'Garcia', 'L987654');

INSERT INTO vehiculo (id, placa, marca, modelo, kilometraje, sucursal_id, conductor_id) VALUES(1, 'ABC123', 'Toyota', 'Corolla', 50000, 1, 1);
INSERT INTO vehiculo (id, placa, marca, modelo, kilometraje, sucursal_id, conductor_id) VALUES(2, 'DEF456', 'Honda', 'Civic', 60000, 2, 2);
INSERT INTO vehiculo (id, placa, marca, modelo, kilometraje, sucursal_id, conductor_id) VALUES(3, 'GHI789', 'Ford', 'Focus', 70000, 3, 3);


INSERT INTO almacen (id, sucursal_id) VALUES(1, 1);
INSERT INTO almacen (id, sucursal_id) VALUES(2, 2);
INSERT INTO almacen (id, sucursal_id) VALUES(3, 3);

INSERT INTO producto (id, nombre, descripcion, cantidad_en_stock, stock_minimo) VALUES(1, 'Aceite', 'Aceite para motor', 100, 20);
INSERT INTO producto (id, nombre, descripcion, cantidad_en_stock, stock_minimo) VALUES(2, 'Filtro de aire', 'Filtro de aire para motor', 50, 10);
INSERT INTO producto (id, nombre, descripcion, cantidad_en_stock, stock_minimo) VALUES(3, 'Batería', 'Batería de coche', 30, 5);

INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(1, 1);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(1, 2);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(1, 3);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(2, 1);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(2, 2);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(2, 3);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(3, 1);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(3, 2);
INSERT INTO almacen_producto (almacen_id, producto_id) VALUES(3, 3);

INSERT INTO orden_trabajo (id, descripcion, fecha_creacion, fecha_fin, estado, vehiculo_id) VALUES (1, 'Cambio de aceite y revisión general', '2023-05-01 10:00:00', NULL, 'Pendiente', 1);
INSERT INTO orden_trabajo (id, descripcion, fecha_creacion, fecha_fin, estado, vehiculo_id) VALUES (2, 'Reparación de frenos', '2023-06-01 11:00:00', NULL, 'Pendiente', 2);
INSERT INTO orden_trabajo (id, descripcion, fecha_creacion, fecha_fin, estado, vehiculo_id) VALUES (3, 'Mantenimiento de motor', '2023-07-01 12:00:00', NULL, 'Pendiente', 3);


INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(1, 'Cambio de aceite', '2023-05-01 10:30:00', NULL, 'Pendiente', 1, 1);
INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(2, 'Revisión de filtro de aire', '2023-05-01 11:00:00', NULL, 'Pendiente', null, 1);
INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(3, 'Reparación de discos de freno', '2023-06-01 11:30:00', NULL, 'Pendiente', 2, 2);
INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(4, 'Cambio de pastillas de freno', '2023-06-01 12:00:00', NULL, 'Pendiente', null, 2);
INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(5, 'Limpieza de inyectores', '2023-07-01 12:30:00', NULL, 'En Progreso', 3, 3);
INSERT INTO tarea (id, descripcion, fecha_inicio, fecha_fin, estado, tecnico_id, orden_trabajo_id) VALUES(6, 'Ajuste de válvulas', '2023-07-01 13:00:00', NULL, 'Pendiente', null, 3);


INSERT INTO usuario_orden_trabajo (id, orden_trabajo_id, usuario_id) VALUES(1, 1, 1);
INSERT INTO usuario_orden_trabajo (id, orden_trabajo_id, usuario_id) VALUES(2, 2, 2);
INSERT INTO usuario_orden_trabajo (id, orden_trabajo_id, usuario_id) VALUES(3, 3, 3);


INSERT INTO compra (id, fecha_orden, estado, proveedor_id) VALUES (1, '2023-04-01', 'Completada', 1);
INSERT INTO compra (id, fecha_orden, estado, proveedor_id) VALUES (2, '2023-05-01', 'Pendiente', 2);
INSERT INTO compra (id, fecha_orden, estado, proveedor_id) VALUES (3, '2023-06-01', 'Completada', 3);


INSERT INTO compra_producto (id, compra_id, producto_id, cantidad) VALUES (1, 1, 1, 50);
INSERT INTO compra_producto (id, compra_id, producto_id, cantidad) VALUES (2, 1, 2, 30);
INSERT INTO compra_producto (id, compra_id, producto_id, cantidad) VALUES (3, 2, 3, 20);
INSERT INTO compra_producto (id, compra_id, producto_id, cantidad) VALUES (4, 3, 1, 10);
INSERT INTO compra_producto (id, compra_id, producto_id, cantidad) VALUES (5, 3, 3, 5);


INSERT INTO reporte_fallo (id, descripcion, fecha_reporte, estado, vehiculo_id, tecnico_id) VALUES(1, 'Fallo en el sistema de frenos', '2023-03-01', 'Abierto', 1, 1);
INSERT INTO reporte_fallo (id, descripcion, fecha_reporte, estado, vehiculo_id, tecnico_id) VALUES(2, 'Problema con la batería', '2023-04-01', 'Cerrado', 2, 2);
INSERT INTO reporte_fallo (id, descripcion, fecha_reporte, estado, vehiculo_id, tecnico_id) VALUES(3, 'Fallo en la dirección', '2023-05-01', 'Abierto', 3, 3);


INSERT INTO solicitud_repuesto (id, descripcion, estado, tecnico_id, fecha_creacion) VALUES (1, 'Solicitud de aceite y filtros', 'Pendiente', 1, '2023-02-01 09:00:00');
INSERT INTO solicitud_repuesto (id, descripcion, estado, tecnico_id, fecha_creacion) VALUES (2, 'Solicitud de pastillas de freno', 'Aprobada', 2, '2023-03-01 10:00:00');
INSERT INTO solicitud_repuesto (id, descripcion, estado, tecnico_id, fecha_creacion) VALUES (3, 'Solicitud de batería', 'Rechazada', 3, '2023-04-01 11:00:00');


INSERT INTO solicitud_repuesto_producto (solicitud_repuesto_id, producto_id) VALUES(1, 1);
INSERT INTO solicitud_repuesto_producto (solicitud_repuesto_id, producto_id) VALUES(1, 2);
INSERT INTO solicitud_repuesto_producto (solicitud_repuesto_id, producto_id) VALUES(2, 3);
INSERT INTO solicitud_repuesto_producto (solicitud_repuesto_id, producto_id) VALUES(3, 3);


