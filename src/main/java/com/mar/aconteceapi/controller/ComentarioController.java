package com.mar.aconteceapi.controller;

import com.mar.aconteceapi.entity.Comentario;
import com.mar.aconteceapi.service.ComentarioService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Obtener todos los comentarios
    @GetMapping
    public ResponseEntity<List<Comentario>> obtenerTodos() {
        return ResponseEntity.ok(comentarioService.obtenerTodos());
    }

    // Obtener comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.obtenerPorId(id));
    }

    // Obtener comentarios de un evento
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Comentario>> obtenerPorEvento(
            @PathVariable Long eventoId) {
        return ResponseEntity.ok(
                comentarioService.obtenerPorEvento(eventoId));
    }

    // Obtener comentarios de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comentario>> obtenerPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                comentarioService.obtenerPorUsuario(usuarioId));
    }

    // Obtener comentarios visibles
    @GetMapping("/visibles")
    public ResponseEntity<List<Comentario>> obtenerVisibles() {
        return ResponseEntity.ok(comentarioService.obtenerVisibles());
    }

    // Buscar comentarios por puntuación
    @GetMapping("/buscar/puntuacion")
    public ResponseEntity<List<Comentario>> buscarPorPuntuacion(
            @RequestParam Integer puntuacion) {
        return ResponseEntity.ok(
                comentarioService.buscarPorPuntuacion(puntuacion));
    }

    // Crear comentario
    @PostMapping
    public ResponseEntity<Comentario> crear(
            @Valid @RequestBody Comentario comentario) {
        Comentario nuevo = comentarioService.crear(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar comentario
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Comentario comentario) {
        return ResponseEntity.ok(
                comentarioService.actualizar(id, comentario));
    }

    // Eliminar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}