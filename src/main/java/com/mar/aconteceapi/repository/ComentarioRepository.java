package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByEventoId(Long eventoId);

    List<Comentario> findByUsuarioId(Long usuarioId);

    List<Comentario> findByVisibleTrue();

    List<Comentario> findByPuntuacion(Integer puntuacion);
}