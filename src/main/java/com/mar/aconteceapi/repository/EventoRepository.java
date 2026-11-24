package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    List<Evento> findByUbicacionContainingIgnoreCase(String ubicacion);

    List<Evento> findByActivoTrue();

    List<Evento> findByOrganizadorId(Long organizadorId);

    @Query("""
            SELECT e FROM Evento e
            WHERE (:titulo IS NULL OR LOWER(e.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            AND (:ubicacion IS NULL OR LOWER(e.ubicacion) LIKE LOWER(CONCAT('%', :ubicacion, '%')))
            AND (:activo IS NULL OR e.activo = :activo)
            """)
    List<Evento> buscarConFiltros(
            @Param("titulo") String titulo,
            @Param("ubicacion") String ubicacion,
            @Param("activo") Boolean activo
    );
}