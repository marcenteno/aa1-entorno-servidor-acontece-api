package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByUsuarioId(Long usuarioId);

    List<Inscripcion> findByEventoId(Long eventoId);

    List<Inscripcion> findByEstado(String estado);

    boolean existsByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);

    @Query("""
            SELECT i FROM Inscripcion i
            WHERE (:estado IS NULL OR LOWER(i.estado) = LOWER(:estado))
            AND (:asistencia IS NULL OR i.asistencia = :asistencia)
            AND (:precioPagado IS NULL OR i.precioPagado = :precioPagado)
            """)
    List<Inscripcion> buscarConFiltros(
            @Param("estado") String estado,
            @Param("asistencia") Boolean asistencia,
            @Param("precioPagado") Float precioPagado
    );
}