--  Desde MONITOREO_ADMIN

-- Insertamos datos en las tablas


-- 1. Insertar Parámetros (pH, Temperatura, etc.)
INSERT INTO Parametro (id_parametro, tipo, unidad)
VALUES (SEQ_PARAMETRO.NEXTVAL, 'Temperatura', '°C');

INSERT INTO Parametro (id_parametro, tipo, unidad)
VALUES (SEQ_PARAMETRO.NEXTVAL, 'pH', 'pH');

INSERT INTO Parametro (id_parametro, tipo, unidad)
VALUES (SEQ_PARAMETRO.NEXTVAL, 'Metales Pesados', 'mg/L');


-- 2. Insertar Ríos
INSERT INTO Rio (id_rio, nombre, longitud)
VALUES (SEQ_RIO.NEXTVAL, 'Río Magdalena', 1528); 

INSERT INTO Rio (id_rio, nombre, longitud)
VALUES (SEQ_RIO.NEXTVAL, 'Rio Cauca', 965); 


-- 3. Insertar Sensores a los rios  id_rio=1 es 'Río Magdalena' y id_rio=2 es 'Rio Cauca'
INSERT INTO Sensores (id_sensor, codigo_sensor, ubicacion, id_rio)
VALUES (SEQ_SENSORES.NEXTVAL, 'SEN-MAG-01', 5.123456, 1); 

INSERT INTO Sensores (id_sensor, codigo_sensor, ubicacion, id_rio)
VALUES (SEQ_SENSORES.NEXTVAL, 'SEN-MAG-02', 5.654321, 1); 

INSERT INTO Sensores (id_sensor, codigo_sensor, ubicacion, id_rio)
VALUES (SEQ_SENSORES.NEXTVAL, 'SEN-CAU-01', 4.987654, 2); 



-- 4. Insertar Muestras

-- (id_sensor=1 es 'SEN-MAG-01' y id_sensor=2 es 'SEN-MAG-02')
--muestra 1 - rio magdalena
INSERT INTO Muestra (id_muestra, fecha_muestra, id_sensor)
VALUES (SEQ_MUESTRA.NEXTVAL, TO_TIMESTAMP('01/09/25 08:00:00', 'DD/MM/YY HH24:MI:SS'), 1);
--muestra 2 - rio magdalena
INSERT INTO Muestra (id_muestra, fecha_muestra, id_sensor)
VALUES (SEQ_MUESTRA.NEXTVAL, TO_TIMESTAMP('02/09/25 09:00:00', 'DD/MM/YY HH24:MI:SS'), 2);
-- muestra 3  tambien asignada al  sensr 1 - rio magdalena
INSERT INTO Muestra (id_muestra, fecha_muestra, id_sensor)
VALUES (SEQ_MUESTRA.NEXTVAL, TO_TIMESTAMP('03/09/25 14:00:00', 'DD/MM/YY HH24:MI:SS'), 1); 

-- muestra para el sensor del rio cauca (id =3)
INSERT INTO Muestra (id_muestra, fecha_muestra, id_sensor)
VALUES (SEQ_MUESTRA.NEXTVAL, TO_TIMESTAMP('04/09/25 01:00:00', 'DD/MM/YY HH24:MI:SS'), 3); 



-- 5. Insertar Mediciones
-- ( id_parametro=1 es 'Temperatura', id_parametro=2 es 'pH', id_parametro=3 es 'Metales Pesados')
-- ( id_muestra=1 es la del 01/09 , id_muestra=2 es la del 02/09, id_muestra=3 es la del 03/09 )

-- Mediciones para Muestra #1 
INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 23.5, 1, 1); -- Temperatura

INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 7.2, 1, 2);  -- pH

INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 0.05, 1, 3);  -- Metales Pesados

-- Mediciones para Muestra #2 
INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 22.1, 2, 1);  -- Temperatura

INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 7.0, 2, 2);  -- pH

-- Mediciones para Muestra #3 
INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 6.8, 3, 2);  -- pH 

-- Mediciones para Muestra #4, la del rio cauca
INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 9.0, 4, 1);  -- Temperatura

INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 8.1, 4, 2);  -- pH 

INSERT INTO Medicion (id_medicion, valor, id_muestra, id_parametro)
VALUES (SEQ_MEDICION.NEXTVAL, 0.02, 4, 3); -- Metales Pesados 

--- Consultas
-- Listar todos los rios  y sus sensores

SELECT r.nombre AS rio, s.codigo_sensor, s.ubicacion
FROM Rio r
JOIN Sensores s ON r.id_rio = s.id_rio;

-- Listamos todas las muestras con rio y sensor

SELECT m.id_muestra, m.fecha_muestra, r.nombre AS rio, s.codigo_sensor
FROM Muestra m
JOIN Sensores s ON m.id_sensor = s.id_sensor -- unimos muestras con sensores
JOIN Rio r ON s.id_rio = r.id_rio; -- y unims sensor con rio


-- Ver mediciones completas  aqui se unen las 5 tablas


SELECT r.nombre AS rio,
       s.codigo_sensor,
       m.fecha_muestra,
       p.tipo AS parametro,
       me.valor,
       p.unidad
FROM Medicion me
JOIN Muestra m ON me.id_muestra = m.id_muestra
JOIN Sensores s ON m.id_sensor = s.id_sensor
JOIN Rio r ON s.id_rio = r.id_rio
JOIN Parametro p ON me.id_parametro = p.id_parametro
ORDER BY m.fecha_muestra;


-- Obtener el promedio de temperatura por río
SELECT r.nombre AS rio,
       ROUND(AVG(me.valor), 2) AS promedio_pH
FROM Medicion me
JOIN Muestra mu ON me.id_muestra = mu.id_muestra
JOIN Sensores s ON mu.id_sensor = s.id_sensor
JOIN Rio r ON s.id_rio = r.id_rio
JOIN Parametro p ON me.id_parametro = p.id_parametro
WHERE p.tipo = 'Temperatura'
GROUP BY r.nombre;

COMMIT;