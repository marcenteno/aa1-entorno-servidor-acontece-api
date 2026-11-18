package com.mar.aconteceapi.service;

import com.mar.aconteceapi.entity.Inscripcion;
import com.mar.aconteceapi.repository.InscripcionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion obtenerPorId(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Inscripción no encontrada: " + id));
    }

    public List<Inscripcion> obtenerPorUsuario(Long usuarioId) {
        return inscripcionRepository.findByUsuarioId(usuarioId);
    }

    public List<Inscripcion> obtenerPorEvento(Long eventoId) {
        return inscripcionRepository.findByEventoId(eventoId);
    }

    public List<Inscripcion> buscarPorEstado(String estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    public boolean existeInscripcion(Long usuarioId, Long eventoId) {
        return inscripcionRepository
                .existsByUsuarioIdAndEventoId(usuarioId, eventoId);
    }

    @Transactional
    public Inscripcion crear(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    public Inscripcion actualizar(Long id, Inscripcion inscripcion) {
        if (!inscripcionRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Inscripción no encontrada: " + id);
        }

        inscripcion.setId(id);
        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Inscripción no encontrada: " + id);
        }

        inscripcionRepository.deleteById(id);
    }
}