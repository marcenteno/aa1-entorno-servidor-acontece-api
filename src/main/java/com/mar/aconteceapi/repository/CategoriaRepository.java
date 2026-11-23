package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<Categoria> findByActivaTrue();

    @Query("""
            SELECT c FROM Categoria c
            WHERE (:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
            AND (:descripcion IS NULL OR LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%')))
            AND (:activa IS NULL OR c.activa = :activa)
            """)
    List<Categoria> buscarConFiltros(
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("activa") Boolean activa
    );
}