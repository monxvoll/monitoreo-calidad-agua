--  Desde MONITOREO_ADMIN

-- Creamos las 5 tablas principales 

-- 1. Tabla Rio
-- Almacena los cuerpos de agua a estudiar
CREATE TABLE Rio (
    id_rio NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    longitud NUMBER(10, 2) -- de tipo decimal
);

-- 2. Tabla Sensores
-- Dispositivos asociados a un río en una ubicación específica
CREATE TABLE Sensores (
    id_sensor NUMBER PRIMARY KEY,
    codigo_sensor VARCHAR2(50) NOT NULL,
    ubicacion NUMBER(10, 6), -- Para coordenadas, tambien en decimal
    id_rio NUMBER,
    CONSTRAINT fk_sensor_rio FOREIGN KEY (id_rio) REFERENCES Rio(id_rio) -- Relación 1:N con Rio
);

-- 3. Tabla Muestra
-- Registro generado por un sensor en una fecha y hora
CREATE TABLE Muestra (
    id_muestra NUMBER PRIMARY KEY,
    fecha_muestra TIMESTAMP, 
    id_sensor NUMBER,
    CONSTRAINT fk_muestra_sensor FOREIGN KEY (id_sensor) REFERENCES Sensores(id_sensor) -- Relación 1:N con Sensores
);

-- 4. Tabla Parametro
-- Define los tipos de datos que se miden (pH, Temperatura) etc..
CREATE TABLE Parametro (
    id_parametro NUMBER PRIMARY KEY,
    tipo VARCHAR2(50) NOT NULL, -- 'pH', 'Metales Pesados' etc.
    unidad VARCHAR2(20) -- 'mg/L', '°C' o demass
);

-- 5. Tabla Medicion
-- Vincula una muestra con un parámetro y almacena el valor numérico
CREATE TABLE Medicion (
    id_medicion NUMBER PRIMARY KEY,
    valor NUMBER(10, 3) NOT NULL, -- valor numerico medido
    id_muestra NUMBER,
    id_parametro NUMBER,
    CONSTRAINT fk_medicion_muestra FOREIGN KEY (id_muestra) REFERENCES Muestra(id_muestra), -- Relación 1:N con Muestra
    CONSTRAINT fk_medicion_parametro FOREIGN KEY (id_parametro) REFERENCES Parametro(id_parametro) -- Relación con Parametro
);

-- CREACIÓN DE SECUENCIAS
CREATE SEQUENCE SEQ_RIO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_SENSORES START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_MUESTRA START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PARAMETRO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_MEDICION START WITH 1 INCREMENT BY 1;

COMMIT;

