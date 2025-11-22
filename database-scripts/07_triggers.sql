-- Desde MONITOREO_ADMIN

-- Tabla de Auditoria para registrar todas las operaciones
CREATE TABLE Auditoria (
    id_auditoria NUMBER PRIMARY KEY,
    nombre_tabla VARCHAR2(50) NOT NULL,
    operacion VARCHAR2(10) NOT NULL, -- INSERT, UPDATE, DELETE
    id_registro NUMBER,
    usuario VARCHAR2(100),
    fecha_operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datos_antiguos CLOB,
    datos_nuevos CLOB
);

-- Secuencia para la tabla de auditoria
CREATE SEQUENCE SEQ_AUDITORIA START WITH 1 INCREMENT BY 1;


-- TRIGGERS PARA LA TABLA RIO

-- Trigger para INSERT en Rio
CREATE OR REPLACE TRIGGER trg_audit_rio_insert
AFTER INSERT ON Rio
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Rio',
        'INSERT',
        :NEW.id_rio,
        USER,
        'nombre: ' || :NEW.nombre || ', longitud: ' || :NEW.longitud
    );
END;
/

-- Trigger para UPDATE en Rio
CREATE OR REPLACE TRIGGER trg_audit_rio_update
AFTER UPDATE ON Rio
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Rio',
        'UPDATE',
        :NEW.id_rio,
        USER,
        'nombre: ' || :OLD.nombre || ', longitud: ' || :OLD.longitud,
        'nombre: ' || :NEW.nombre || ', longitud: ' || :NEW.longitud
    );
END;
/

-- Trigger para DELETE en Rio
CREATE OR REPLACE TRIGGER trg_audit_rio_delete
AFTER DELETE ON Rio
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Rio',
        'DELETE',
        :OLD.id_rio,
        USER,
        'nombre: ' || :OLD.nombre || ', longitud: ' || :OLD.longitud
    );
END;
/


-- TRIGGERS PARA LA TABLA SENSORES


-- Trigger para INSERT en Sensores
CREATE OR REPLACE TRIGGER trg_audit_sensores_insert
AFTER INSERT ON Sensores
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Sensores',
        'INSERT',
        :NEW.id_sensor,
        USER,
        'codigo_sensor: ' || :NEW.codigo_sensor || ', ubicacion: ' || :NEW.ubicacion || ', id_rio: ' || :NEW.id_rio
    );
END;
/

-- Trigger para UPDATE en Sensores
CREATE OR REPLACE TRIGGER trg_audit_sensores_update
AFTER UPDATE ON Sensores
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Sensores',
        'UPDATE',
        :NEW.id_sensor,
        USER,
        'codigo_sensor: ' || :OLD.codigo_sensor || ', ubicacion: ' || :OLD.ubicacion || ', id_rio: ' || :OLD.id_rio,
        'codigo_sensor: ' || :NEW.codigo_sensor || ', ubicacion: ' || :NEW.ubicacion || ', id_rio: ' || :NEW.id_rio
    );
END;
/

-- Trigger para DELETE en Sensores
CREATE OR REPLACE TRIGGER trg_audit_sensores_delete
AFTER DELETE ON Sensores
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Sensores',
        'DELETE',
        :OLD.id_sensor,
        USER,
        'codigo_sensor: ' || :OLD.codigo_sensor || ', ubicacion: ' || :OLD.ubicacion || ', id_rio: ' || :OLD.id_rio
    );
END;
/


-- TRIGGERS PARA LA TABLA MUESTRA


-- Trigger para INSERT en Muestra
CREATE OR REPLACE TRIGGER trg_audit_muestra_insert
AFTER INSERT ON Muestra
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Muestra',
        'INSERT',
        :NEW.id_muestra,
        USER,
        'fecha_muestra: ' || TO_CHAR(:NEW.fecha_muestra, 'DD/MM/YYYY HH24:MI:SS') || ', id_sensor: ' || :NEW.id_sensor
    );
END;
/

-- Trigger para UPDATE en Muestra
CREATE OR REPLACE TRIGGER trg_audit_muestra_update
AFTER UPDATE ON Muestra
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Muestra',
        'UPDATE',
        :NEW.id_muestra,
        USER,
        'fecha_muestra: ' || TO_CHAR(:OLD.fecha_muestra, 'DD/MM/YYYY HH24:MI:SS') || ', id_sensor: ' || :OLD.id_sensor,
        'fecha_muestra: ' || TO_CHAR(:NEW.fecha_muestra, 'DD/MM/YYYY HH24:MI:SS') || ', id_sensor: ' || :NEW.id_sensor
    );
END;
/

-- Trigger para DELETE en Muestra
CREATE OR REPLACE TRIGGER trg_audit_muestra_delete
AFTER DELETE ON Muestra
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Muestra',
        'DELETE',
        :OLD.id_muestra,
        USER,
        'fecha_muestra: ' || TO_CHAR(:OLD.fecha_muestra, 'DD/MM/YYYY HH24:MI:SS') || ', id_sensor: ' || :OLD.id_sensor
    );
END;
/


-- TRIGGERS PARA LA TABLA PARAMETRO


-- Trigger para INSERT en Parametro
CREATE OR REPLACE TRIGGER trg_audit_parametro_insert
AFTER INSERT ON Parametro
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Parametro',
        'INSERT',
        :NEW.id_parametro,
        USER,
        'tipo: ' || :NEW.tipo || ', unidad: ' || :NEW.unidad
    );
END;
/

-- Trigger para UPDATE en Parametro
CREATE OR REPLACE TRIGGER trg_audit_parametro_update
AFTER UPDATE ON Parametro
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Parametro',
        'UPDATE',
        :NEW.id_parametro,
        USER,
        'tipo: ' || :OLD.tipo || ', unidad: ' || :OLD.unidad,
        'tipo: ' || :NEW.tipo || ', unidad: ' || :NEW.unidad
    );
END;
/

-- Trigger para DELETE en Parametro
CREATE OR REPLACE TRIGGER trg_audit_parametro_delete
AFTER DELETE ON Parametro
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Parametro',
        'DELETE',
        :OLD.id_parametro,
        USER,
        'tipo: ' || :OLD.tipo || ', unidad: ' || :OLD.unidad
    );
END;
/


-- TRIGGERS PARA LA TABLA MEDICION


-- Trigger para INSERT en Medicion
CREATE OR REPLACE TRIGGER trg_audit_medicion_insert
AFTER INSERT ON Medicion
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Medicion',
        'INSERT',
        :NEW.id_medicion,
        USER,
        'valor: ' || :NEW.valor || ', id_muestra: ' || :NEW.id_muestra || ', id_parametro: ' || :NEW.id_parametro
    );
END;
/

-- Trigger para UPDATE en Medicion
CREATE OR REPLACE TRIGGER trg_audit_medicion_update
AFTER UPDATE ON Medicion
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos, datos_nuevos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Medicion',
        'UPDATE',
        :NEW.id_medicion,
        USER,
        'valor: ' || :OLD.valor || ', id_muestra: ' || :OLD.id_muestra || ', id_parametro: ' || :OLD.id_parametro,
        'valor: ' || :NEW.valor || ', id_muestra: ' || :NEW.id_muestra || ', id_parametro: ' || :NEW.id_parametro
    );
END;
/

-- Trigger para DELETE en Medicion
CREATE OR REPLACE TRIGGER trg_audit_medicion_delete
AFTER DELETE ON Medicion
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (id_auditoria, nombre_tabla, operacion, id_registro, usuario, datos_antiguos)
    VALUES (
        SEQ_AUDITORIA.NEXTVAL,
        'Medicion',
        'DELETE',
        :OLD.id_medicion,
        USER,
        'valor: ' || :OLD.valor || ', id_muestra: ' || :OLD.id_muestra || ', id_parametro: ' || :OLD.id_parametro
    );
END;
/

COMMIT;

-- Consulta para ver la auditoria
-- SELECT * FROM Auditoria ORDER BY fecha_operacion DESC;
