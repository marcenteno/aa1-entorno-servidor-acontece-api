package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByUsuarioId(Long usuarioId);

    List<Inscripcion> findByEventoId(Long eventoId);

    List<Inscripcion> findByEstado(String estado);

    boolean existsByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
}