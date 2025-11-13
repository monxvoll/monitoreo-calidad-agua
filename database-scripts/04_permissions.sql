-- Desde MONITOREO-ADMIN ya que es el owner de las tablas



-- --- 1. Permisos para ROL_GESTOR (CRUD en tablas maestras) ---
--Este rol es el encargado de manipular la informacion en las tablas principales
--Es decir es quien configura las cosas que se van a medir
GRANT SELECT, INSERT, UPDATE, DELETE ON Rio TO ROL_GESTOR;
GRANT SELECT, INSERT, UPDATE, DELETE ON Sensores TO ROL_GESTOR;
GRANT SELECT, INSERT, UPDATE, DELETE ON Parametro TO ROL_GESTOR;

-- Permisos para las secuencias
GRANT SELECT ON SEQ_RIO TO ROL_GESTOR;
GRANT SELECT ON SEQ_SENSORES TO ROL_GESTOR;
GRANT SELECT ON SEQ_PARAMETRO TO ROL_GESTOR;


-- --- 2. Permisos para ROL_RECOLECCION (Solo insertar datos) ---
-- Este rol en cambio, se dedicara a toda la parte de la insercion de nuevas muestras y mediciones
GRANT INSERT ON Muestra TO ROL_RECOLECCION;
GRANT INSERT ON Medicion TO ROL_RECOLECCION;
-- Permisos para las secuencias
GRANT SELECT ON SEQ_MUESTRA TO ROL_RECOLECCION;
GRANT SELECT ON SEQ_MEDICION TO ROL_RECOLECCION;
-- Permisos de lectura para buscar IDs
GRANT SELECT ON Sensores TO ROL_RECOLECCION;
GRANT SELECT ON Parametro TO ROL_RECOLECCION;


-- --- 3. Permisos para ROL_ANALISTA (Solo lectura de todo) ---
-- Este rol esta en focado en unicamente la lectura, por ejemplo
-- se podria usar para ejecutar la consulta del promedio
GRANT SELECT ON Rio TO ROL_ANALISTA;
GRANT SELECT ON Sensores TO ROL_ANALISTA;
GRANT SELECT ON Muestra TO ROL_ANALISTA;
GRANT SELECT ON Parametro TO ROL_ANALISTA;
GRANT SELECT ON Medicion TO ROL_ANALISTA;

COMMIT;

