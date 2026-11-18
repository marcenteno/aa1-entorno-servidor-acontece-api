package com.mar.aconteceapi.service;

import com.mar.aconteceapi.entity.Comentario;
import com.mar.aconteceapi.repository.ComentarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> obtenerTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario obtenerPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Comentario no encontrado: " + id));
    }

    public List<Comentario> obtenerPorEvento(Long eventoId) {
        return comentarioRepository.findByEventoId(eventoId);
    }

    public List<Comentario> obtenerPorUsuario(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    public List<Comentario> obtenerVisibles() {
        return comentarioRepository.findByVisibleTrue();
    }

    public List<Comentario> buscarPorPuntuacion(Integer puntuacion) {
        return comentarioRepository.findByPuntuacion(puntuacion);
    }

    @Transactional
    public Comentario crear(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Transactional
    public Comentario actualizar(Long id, Comentario comentario) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Comentario no encontrado: " + id);
        }

        comentario.setId(id);
        return comentarioRepository.save(comentario);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Comentario no encontrado: " + id);
        }

        comentarioRepository.deleteById(id);
    }
}