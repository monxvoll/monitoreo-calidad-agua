# Sistema de Monitoreo de Calidad del Agua en Rios

Sistema completo de monitoreo ambiental que permite registrar múltiples ríos, asociar sensores, recopilar muestras de calidad del agua y medir diferentes parámetros como pH, temperatura y metales pesados.

## Arquitectura del Proyecto

### Base de Datos (Oracle SQL Developer)
- 5 tablas relacionales: Rio, Sensores, Muestra, Parametro, Medicion
- Sistema de roles y permisos (ROL_GESTOR, ROL_RECOLECCION, ROL_ANALISTA)
- Triggers de auditoría para todas las operaciones

### Backend (Spring Boot - Java 21)
- API REST con endpoints CRUD
- Integración con Oracle Database mediante JPA/Hibernate
- DTOs para transferencia de datos complejos

---

## Estructura del Proyecto

```
monitoreo-calidad-agua/
├── database-scripts/          # Scripts SQL en orden de ejecución
│   ├── 00_setup_user.sql     # Creación del usuario MONITOREO_ADMIN
│   ├── 01_schema.sql         # Creación de tablas y secuencias
│   ├── 02_test_data.sql      # Datos de prueba
│   ├── 03_roles.sql          # Definición de roles
│   ├── 04_permissions.sql    # Asignación de permisos
│   ├── 05_create_users.sql   # Creación de usuarios
│   ├── 06_assign_roles.sql   # Asignación de roles a usuarios
│   └── 07_triggers.sql       # Triggers de auditoría
│
└── backend-spring/            # Aplicación Spring Boot
    ├── pom.xml
    └── src/main/java/com/monitoreo/agua/
        ├── entity/            # Entidades JPA (Rio, Sensor, Muestra, etc.)
        ├── repository/        # Repositorios JPA
        ├── service/           # Lógica de negocio
        ├── controller/        # Controladores REST
        └── dto/               # Data Transfer Objects
```

---

## Base de Datos

### Modelo de Datos

**Rio** → Almacena información de los cuerpos de agua
- id_rio (PK)
- nombre
- longitud

**Sensores** → Dispositivos de medición ubicados en los ríos
- id_sensor (PK)
- codigo_sensor
- ubicacion
- id_rio (FK → Rio)

**Muestra** → Registro de muestreo en una fecha específica
- id_muestra (PK)
- fecha_muestra
- id_sensor (FK → Sensores)

**Parametro** → Tipos de mediciones (pH, Temperatura, Metales Pesados)
- id_parametro (PK)
- tipo
- unidad

**Medicion** → Valores medidos para cada parámetro en cada muestra
- id_medicion (PK)
- valor
- id_muestra (FK → Muestra)
- id_parametro (FK → Parametro)

**Auditoria** → Registro de todas las operaciones (INSERT, UPDATE, DELETE)
- id_auditoria (PK)
- nombre_tabla
- operacion
- id_registro
- usuario
- fecha_operacion
- datos_antiguos
- datos_nuevos

### Roles y Permisos

**ROL_GESTOR**: CRUD completo en Rio, Sensores, Parametro
**ROL_RECOLECCION**: INSERT en Muestra y Medicion, SELECT en Sensores y Parametro
**ROL_ANALISTA**: Solo SELECT en todas las tablas

### Instalación de Base de Datos

1. Conectarse a Oracle SQL Developer como usuario **SYSTEM**
2. Ejecutar los scripts en orden numérico (00 → 07)

```sql
-- Ejemplo de ejecución:
@00_setup_user.sql
@01_schema.sql
@02_test_data.sql
-- ... etc
```

---

## Backend - Spring Boot

### Configuración

**Requisitos:**
- Java 21
- Maven 3.6+
- Oracle Database XE configurado

**application.properties:**
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XEPDB1
spring.datasource.username=MONITOREO_ADMIN
spring.datasource.password=12345
```

### Compilación y Ejecución

```bash
cd backend-spring
mvn clean install
mvn spring-boot:run
```

La aplicación se ejecutará en `http://localhost:8080`

---

## API Endpoints

### CRUD - Ríos
- `GET /api/rios` - Obtener todos los ríos
- `GET /api/rios/{id}` - Obtener río por ID
- `POST /api/rios` - Crear nuevo río
- `PUT /api/rios/{id}` - Actualizar río
- `DELETE /api/rios/{id}` - Eliminar río

### CRUD - Sensores
- `GET /api/sensores` - Obtener todos los sensores
- `GET /api/sensores/{id}` - Obtener sensor por ID
- `POST /api/sensores` - Crear nuevo sensor
- `PUT /api/sensores/{id}` - Actualizar sensor
- `DELETE /api/sensores/{id}` - Eliminar sensor

### CRUD - Parámetros
- `GET /api/parametros` - Obtener todos los parámetros
- `GET /api/parametros/{id}` - Obtener parámetro por ID
- `POST /api/parametros` - Crear nuevo parámetro
- `PUT /api/parametros/{id}` - Actualizar parámetro
- `DELETE /api/parametros/{id}` - Eliminar parámetro

### Muestras 
- `POST /api/muestras` - Registrar muestra con múltiples mediciones

**Ejemplo de JSON:**
```json
{
  "idSensor": 1,
  "fechaMuestra": "2025-09-01T08:00:00",
  "mediciones": [
    {
      "idParametro": 1,
      "valor": 23.5
    },
    {
      "idParametro": 2,
      "valor": 7.2
    }
  ]
}
```

### Reportes
- `GET /api/reportes/rios-y-sensores` - Listar ríos con sus sensores (JOIN)
- `GET /api/reportes/mediciones-completas` - Mediciones completas (JOIN de 5 tablas)
- `GET /api/reportes/promedio-ph` - Promedio de pH por río (AVG + GROUP BY)

## Pruebas con Postman

* **URL Base:** `http://localhost:8080/api`
* **Headers (Encabezados):**
    * `Content-Type`: `application/json`

---

### 1. Gestión de Ríos (CRUD)

* **Crear un Río (POST)**
    * **Endpoint:** `/rios`
    * **Body (JSON):**
        ```json
        {
            "nombre": "Río Amazonas",
            "longitud": 6400.00
        }
        ```

* **Listar Ríos (GET)**
    * **Endpoint:** `/rios`

* **Actualizar Río (PUT)**
    * **Endpoint:** `/rios/{id}` (ej. `/rios/1`)
    * **Body (JSON):**
        ```json
        {
            "nombre": "Río Magdalena - Monitoreado",
            "longitud": 1528.00
        }
        ```

---

### 2. Registro de Muestras 

Este es el endpoint principal que registra una muestra y sus mediciones en una sola peticion

* **Registrar Muestra y Mediciones (POST)**
    * **Endpoint:** `/muestras`
    * **Body (JSON):**
        ```json
        {
            "idSensor": 1,
            "fechaMuestra": "2025-11-21T10:00:00",
            "mediciones": [
                {
                    "idParametro": 1,
                    "valor": 24.5
                },
                {
                    "idParametro": 2,
                    "valor": 7.2
                }
            ]
        }
        ```

---

### 3. Reportes y Consultas

Estos endpoints ejecutan consultas complejas (JOINs y Agregaciones) en la base de datos.

* **Reporte de Ríos y Sensores (GET)**
    * **Endpoint:** `/reportes/rios-y-sensores`
    * *Descripción: Muestra qué sensores están instalados en cada río.*

* **Reporte Completo de Mediciones (GET)**
    * **Endpoint:** `/reportes/mediciones-completas`
    * *Descripción: Une las 5 tablas para mostrar el detalle de cada medición.*

* **Promedio de pH por Río (GET)**
    * **Endpoint:** `/reportes/promedio-ph`
    * *Descripción: Calcula el promedio de acidez agrupado por río.*

---

### 4. Auditoría y Seguridad

Consulta el historial de cambios registrado automáticamente por los Triggers de Oracle.

* **Ver Historial de Auditoría (GET)**
    * **Endpoint:** `/auditoria`
    * *Descripción: Muestra todas las operaciones (INSERT, UPDATE, DELETE) realizadas en el sistema, ordenadas de la más reciente a la más antigua.*



## Tecnologías Utilizadas

- **Base de Datos**: Oracle Database XE
- **Backend**: Spring Boot 3.2.0, Java 21
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Dependencias adicionales**: Lombok, Jackson

---

## Autores




---

