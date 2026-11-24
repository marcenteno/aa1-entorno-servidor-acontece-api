package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByEventoId(Long eventoId);

    List<Comentario> findByUsuarioId(Long usuarioId);

    List<Comentario> findByVisibleTrue();

    List<Comentario> findByPuntuacion(Integer puntuacion);

    // Buscar comentarios combinando hasta tres filtros
    @Query("""
            SELECT c FROM Comentario c
            WHERE (:titulo IS NULL OR LOWER(c.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            AND (:puntuacion IS NULL OR c.puntuacion = :puntuacion)
            AND (:visible IS NULL OR c.visible = :visible)
            """)
    List<Comentario> buscarConFiltros(
            @Param("titulo") String titulo,
            @Param("puntuacion") Integer puntuacion,
            @Param("visible") Boolean visible
    );
}