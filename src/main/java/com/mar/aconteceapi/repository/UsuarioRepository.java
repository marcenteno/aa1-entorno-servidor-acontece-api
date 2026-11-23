package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Usuario> findByNombre(String nombre);

    @Query("""
            SELECT u FROM Usuario u
            WHERE (:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
            AND (:apellidos IS NULL OR LOWER(u.apellidos) LIKE LOWER(CONCAT('%', :apellidos, '%')))
            AND (:activo IS NULL OR u.activo = :activo)
            """)
    List<Usuario> buscarConFiltros(
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("activo") Boolean activo
    );
}