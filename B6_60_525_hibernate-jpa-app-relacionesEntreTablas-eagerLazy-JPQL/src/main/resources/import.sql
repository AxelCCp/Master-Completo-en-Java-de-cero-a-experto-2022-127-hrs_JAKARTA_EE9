INSERT INTO clientes(id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES(1,'Andres','Guzman','debito',NULL,NULL),(2,'John','doe','credito',NULL,NULL),(4,'Pepa','doe','credito',NULL,NULL),(5,'Lucy','Martinez','paypal',NULL,NULL),(6,'Luna','Garcia','Debito',NULL,NULL),(8,'John','roe','paypal',NULL,NULL),(9,'Lou','loe','paypal',NULL,NULL),(10,'Majin ','Boo','credito','2023-04-17 14:33:20','2023-04-17 14:40:50'),(11,'algun ','nombre','debito','2023-04-17 14:54:06','2023-04-17 14:54:36');

INSERT INTO alumnos(id, nombre, apellido) VALUES(1, 'Erika', 'Arg');
INSERT INTO alumnos(id, nombre, apellido) VALUES(2, 'Pepe', 'Gon');

INSERT INTO cursos(id, titulo, profesor) VALUES(1, 'Curso de spring', 'Andres');
INSERT INTO cursos(id, titulo, profesor) VALUES(2, 'Curso de jakarta ee9', 'Andres');

INSERT INTO direcciones(calle, numero) VALUES('vaticano', 123);
INSERT INTO direcciones(calle, numero) VALUES('colon', 456);

INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES(1,1);
INSERT INTO tbl_clientes_direcciones (id_cliente, id_direccion) VALUES(1,2);

INSERT INTO clientes_detalles (prime, puntos_acumulados, detalle_id) VALUES(1,8000,1);

INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES(1, 1);
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES(1, 2);