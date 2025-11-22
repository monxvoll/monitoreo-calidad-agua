package com.monitoreo.agua.service;

import com.monitoreo.agua.dto.MedicionCompletaDTO;
import com.monitoreo.agua.dto.PromedioPhDTO;
import com.monitoreo.agua.dto.RioConSensoresDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RioConSensoresDTO> obtenerRiosYSensores() {
        String sql = "SELECT r.nombre AS rio, s.codigo_sensor, s.ubicacion " +
                     "FROM Rio r " +
                     "JOIN Sensores s ON r.id_rio = s.id_rio";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultados = query.getResultList();

        List<RioConSensoresDTO> dtos = new ArrayList<>();
        for (Object[] fila : resultados) {
            RioConSensoresDTO dto = new RioConSensoresDTO();
            dto.setRio((String) fila[0]);
            dto.setCodigoSensor((String) fila[1]);
            dto.setUbicacion(fila[2] != null ? new BigDecimal(fila[2].toString()) : null);
            dtos.add(dto);
        }

        return dtos;
    }

    public List<MedicionCompletaDTO> obtenerMedicionesCompletas() {
        String sql = "SELECT r.nombre AS rio, " +
                     "       s.codigo_sensor, " +
                     "       m.fecha_muestra, " +
                     "       p.tipo AS parametro, " +
                     "       me.valor, " +
                     "       p.unidad " +
                     "FROM Medicion me " +
                     "JOIN Muestra m ON me.id_muestra = m.id_muestra " +
                     "JOIN Sensores s ON m.id_sensor = s.id_sensor " +
                     "JOIN Rio r ON s.id_rio = r.id_rio " +
                     "JOIN Parametro p ON me.id_parametro = p.id_parametro " +
                     "ORDER BY m.fecha_muestra";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultados = query.getResultList();

        List<MedicionCompletaDTO> dtos = new ArrayList<>();
        for (Object[] fila : resultados) {
            MedicionCompletaDTO dto = new MedicionCompletaDTO();
            dto.setRio((String) fila[0]);
            dto.setCodigoSensor((String) fila[1]);

            // Convertir Timestamp a LocalDateTime
            if (fila[2] instanceof Timestamp) {
                dto.setFechaMuestra(((Timestamp) fila[2]).toLocalDateTime());
            }

            dto.setParametro((String) fila[3]);
            dto.setValor(fila[4] != null ? new BigDecimal(fila[4].toString()) : null);
            dto.setUnidad((String) fila[5]);
            dtos.add(dto);
        }

        return dtos;
    }

    public List<PromedioPhDTO> obtenerPromedioPh() {
        String sql = "SELECT r.nombre AS rio, " +
                     "       ROUND(AVG(me.valor), 2) AS promedio_ph " +
                     "FROM Medicion me " +
                     "JOIN Muestra mu ON me.id_muestra = mu.id_muestra " +
                     "JOIN Sensores s ON mu.id_sensor = s.id_sensor " +
                     "JOIN Rio r ON s.id_rio = r.id_rio " +
                     "JOIN Parametro p ON me.id_parametro = p.id_parametro " +
                     "WHERE p.tipo = 'pH' " +
                     "GROUP BY r.nombre";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultados = query.getResultList();

        List<PromedioPhDTO> dtos = new ArrayList<>();
        for (Object[] fila : resultados) {
            PromedioPhDTO dto = new PromedioPhDTO();
            dto.setRio((String) fila[0]);
            dto.setPromedioPh(fila[1] != null ? new BigDecimal(fila[1].toString()) : null);
            dtos.add(dto);
        }

        return dtos;
    }
}