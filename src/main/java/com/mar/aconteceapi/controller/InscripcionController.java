package com.mar.aconteceapi.controller;

import com.mar.aconteceapi.entity.Inscripcion;
import com.mar.aconteceapi.service.InscripcionService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // Obtener todas las inscripciones
    @GetMapping
    public ResponseEntity<List<Inscripcion>> obtenerTodas() {
        return ResponseEntity.ok(inscripcionService.obtenerTodas());
    }

    // Obtener inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }

    // Obtener inscripciones de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                inscripcionService.obtenerPorUsuario(usuarioId));
    }

    // Obtener inscripciones de un evento
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorEvento(
            @PathVariable Long eventoId) {
        return ResponseEntity.ok(
                inscripcionService.obtenerPorEvento(eventoId));
    }

    // Buscar inscripciones por estado
    @GetMapping("/buscar/estado")
    public ResponseEntity<List<Inscripcion>> buscarPorEstado(
            @RequestParam String estado) {
        return ResponseEntity.ok(
                inscripcionService.buscarPorEstado(estado));
    }

    // Buscar inscripciones combinando hasta tres filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Inscripcion>> buscarConFiltros(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Boolean asistencia,
            @RequestParam(required = false) Float precioPagado) {

        return ResponseEntity.ok(
                inscripcionService.buscarConFiltros(
                        estado, asistencia, precioPagado));
    }

    // Crear inscripción
    @PostMapping
    public ResponseEntity<Inscripcion> crear(
            @Valid @RequestBody Inscripcion inscripcion) {
        Inscripcion nueva = inscripcionService.crear(inscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar inscripción
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(
                inscripcionService.actualizar(id, inscripcion));
    }

    // Eliminar inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}