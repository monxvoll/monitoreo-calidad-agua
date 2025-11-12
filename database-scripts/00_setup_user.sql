-- Lo siguiente se hace desde system

alter session set container = XEPDB1; -- nos conectamos al contenedor correcto

-- Ahora creamos un usuario para el proyecto llamado MONITERO_ADMIN

CREATE USER MONITOREO_ADMIN IDENTIFIED BY 12345
DEFAULT TABLESPACE USERS
QUOTA 50M ON USERS;

-- le damos los permisos a ese usuario

GRANT CREATE SESSION, CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE TRIGGER TO MONITOREO_ADMIN;

