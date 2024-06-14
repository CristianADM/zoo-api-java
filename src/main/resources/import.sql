/* Creamos algunos usuarios con sus roles */
INSERT INTO usuarios (correo, contrasenna) VALUES ('admin@cadm.com', '$2a$10$k6psPOuRmqWUNesqasoTFeWjiNkwATgnOHUnYHlmv4Cs0jR.pke4K');

INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_EMPLEADO');

INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES (1, 1);
INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES (1, 2);