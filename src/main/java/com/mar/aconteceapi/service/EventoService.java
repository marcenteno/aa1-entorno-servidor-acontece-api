package com.mar.aconteceapi.service;

import com.mar.aconteceapi.entity.Evento;
import com.mar.aconteceapi.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Evento no encontrado: " + id));
    }

    public List<Evento> buscarPorTitulo(String titulo) {
        return eventoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Evento> buscarPorUbicacion(String ubicacion) {
        return eventoRepository.findByUbicacionContainingIgnoreCase(ubicacion);
    }

    public List<Evento> obtenerActivos() {
        return eventoRepository.findByActivoTrue();
    }

    public List<Evento> obtenerPorOrganizador(Long organizadorId) {
        return eventoRepository.findByOrganizadorId(organizadorId);
    }

    @Transactional
    public Evento crear(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento actualizar(Long id, Evento evento) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Evento no encontrado: " + id);
        }

        evento.setId(id);
        return eventoRepository.save(evento);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Evento no encontrado: " + id);
        }

        eventoRepository.deleteById(id);
    }
}