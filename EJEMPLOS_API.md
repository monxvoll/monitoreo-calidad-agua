# Ejemplos de Uso de la API

Este documento contiene ejemplos prácticos para probar todos los endpoints de la API.

## Prerequisitos
- Base de datos Oracle configurada y scripts ejecutados
- Aplicación Spring Boot ejecutándose en `http://localhost:8080`

---

## 1. CRUD - Ríos

### Crear un río
```bash
POST http://localhost:8080/api/rios
Content-Type: application/json

{
  "nombre": "Río Orinoco",
  "longitud": 2140.50
}
```

### Obtener todos los ríos
```bash
GET http://localhost:8080/api/rios
```

### Obtener río por ID
```bash
GET http://localhost:8080/api/rios/1
```

### Actualizar un río
```bash
PUT http://localhost:8080/api/rios/1
Content-Type: application/json

{
  "nombre": "Río Magdalena Actualizado",
  "longitud": 1530.00
}
```

### Eliminar un río
```bash
DELETE http://localhost:8080/api/rios/1
```

---

## 2. CRUD - Sensores

### Crear un sensor
```bash
POST http://localhost:8080/api/sensores
Content-Type: application/json

{
  "codigoSensor": "SEN-ORI-01",
  "ubicacion": 8.123456,
  "rio": {
    "idRio": 1
  }
}
```

### Obtener todos los sensores
```bash
GET http://localhost:8080/api/sensores
```

### Obtener sensor por ID
```bash
GET http://localhost:8080/api/sensores/1
```

### Actualizar un sensor
```bash
PUT http://localhost:8080/api/sensores/1
Content-Type: application/json

{
  "codigoSensor": "SEN-MAG-01-UPDATED",
  "ubicacion": 5.200000,
  "rio": {
    "idRio": 1
  }
}
```

### Eliminar un sensor
```bash
DELETE http://localhost:8080/api/sensores/1
```

---

## 3. CRUD - Parámetros

### Crear un parámetro
```bash
POST http://localhost:8080/api/parametros
Content-Type: application/json

{
  "tipo": "Oxígeno Disuelto",
  "unidad": "mg/L"
}
```

### Obtener todos los parámetros
```bash
GET http://localhost:8080/api/parametros
```

### Obtener parámetro por ID
```bash
GET http://localhost:8080/api/parametros/1
```

### Actualizar un parámetro
```bash
PUT http://localhost:8080/api/parametros/1
Content-Type: application/json

{
  "tipo": "Temperatura del Agua",
  "unidad": "°C"
}
```

### Eliminar un parámetro
```bash
DELETE http://localhost:8080/api/parametros/1
```

---

## 4. Endpoint Complejo - Registrar Muestra con Mediciones

Este endpoint permite registrar una muestra junto con múltiples mediciones en una sola petición.

### Ejemplo: Registrar muestra con pH y Temperatura
```bash
POST http://localhost:8080/api/muestras
Content-Type: application/json

{
  "idSensor": 1,
  "fechaMuestra": "2025-09-10T14:30:00",
  "mediciones": [
    {
      "idParametro": 1,
      "valor": 24.5
    },
    {
      "idParametro": 2,
      "valor": 7.8
    }
  ]
}
```

### Ejemplo: Registrar muestra con 3 mediciones
```bash
POST http://localhost:8080/api/muestras
Content-Type: application/json

{
  "idSensor": 2,
  "fechaMuestra": "2025-09-11T09:00:00",
  "mediciones": [
    {
      "idParametro": 1,
      "valor": 22.0
    },
    {
      "idParametro": 2,
      "valor": 6.9
    },
    {
      "idParametro": 3,
      "valor": 0.03
    }
  ]
}
```

---

## 5. Reportes

### Reporte: Ríos y Sensores (JOIN)
Obtiene todos los ríos con sus sensores asociados.

```bash
GET http://localhost:8080/api/reportes/rios-y-sensores
```

**Respuesta esperada:**
```json
[
  {
    "rio": "Río Magdalena",
    "codigoSensor": "SEN-MAG-01",
    "ubicacion": 5.123456
  },
  {
    "rio": "Río Magdalena",
    "codigoSensor": "SEN-MAG-02",
    "ubicacion": 5.654321
  },
  {
    "rio": "Rio Cauca",
    "codigoSensor": "SEN-CAU-01",
    "ubicacion": 4.987654
  }
]
```

### Reporte: Mediciones Completas (JOIN de 5 tablas)
Obtiene todas las mediciones con información completa de río, sensor, muestra y parámetro.

```bash
GET http://localhost:8080/api/reportes/mediciones-completas
```

**Respuesta esperada:**
```json
[
  {
    "rio": "Río Magdalena",
    "codigoSensor": "SEN-MAG-01",
    "fechaMuestra": "2025-09-01T08:00:00",
    "parametro": "Temperatura",
    "valor": 23.5,
    "unidad": "°C"
  },
  {
    "rio": "Río Magdalena",
    "codigoSensor": "SEN-MAG-01",
    "fechaMuestra": "2025-09-01T08:00:00",
    "parametro": "pH",
    "valor": 7.2,
    "unidad": "pH"
  }
]
```

### Reporte: Promedio de pH por Río (AVG + GROUP BY)
Calcula el promedio de pH para cada río.

```bash
GET http://localhost:8080/api/reportes/promedio-ph
```

**Respuesta esperada:**
```json
[
  {
    "rio": "Río Magdalena",
    "promedioPh": 7.00
  },
  {
    "rio": "Rio Cauca",
    "promedioPh": 8.10
  }
]
```

---

## Colección de Postman

Puedes importar estos ejemplos a Postman creando una colección con las siguientes peticiones organizadas por carpetas:

1. **Rios**
   - GET All Rios
   - GET Rio by ID
   - POST Create Rio
   - PUT Update Rio
   - DELETE Delete Rio

2. **Sensores**
   - GET All Sensores
   - GET Sensor by ID
   - POST Create Sensor
   - PUT Update Sensor
   - DELETE Delete Sensor

3. **Parametros**
   - GET All Parametros
   - GET Parametro by ID
   - POST Create Parametro
   - PUT Update Parametro
   - DELETE Delete Parametro

4. **Muestras**
   - POST Registrar Muestra con Mediciones

5. **Reportes**
   - GET Rios y Sensores
   - GET Mediciones Completas
   - GET Promedio pH

---
