package com.mar.aconteceapi.repository;

import com.mar.aconteceapi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    List<Evento> findByUbicacionContainingIgnoreCase(String ubicacion);

    List<Evento> findByActivoTrue();

    List<Evento> findByOrganizadorId(Long organizadorId);
}