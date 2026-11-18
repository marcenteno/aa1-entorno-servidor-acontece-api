package com.mar.aconteceapi.controller;

import com.mar.aconteceapi.entity.Evento;
import com.mar.aconteceapi.service.EventoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodos() {
        return ResponseEntity.ok(eventoService.obtenerTodos());
    }

    // Obtener evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.obtenerPorId(id));
    }

    // Buscar eventos por título
    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Evento>> buscarPorTitulo(
            @RequestParam String titulo) {
        return ResponseEntity.ok(eventoService.buscarPorTitulo(titulo));
    }

    // Buscar eventos por ubicación
    @GetMapping("/buscar/ubicacion")
    public ResponseEntity<List<Evento>> buscarPorUbicacion(
            @RequestParam String ubicacion) {
        return ResponseEntity.ok(eventoService.buscarPorUbicacion(ubicacion));
    }

    // Obtener eventos activos
    @GetMapping("/activos")
    public ResponseEntity<List<Evento>> obtenerActivos() {
        return ResponseEntity.ok(eventoService.obtenerActivos());
    }

    // Obtener eventos de un organizador
    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<Evento>> obtenerPorOrganizador(
            @PathVariable Long organizadorId) {
        return ResponseEntity.ok(
                eventoService.obtenerPorOrganizador(organizadorId));
    }

    // Crear evento
    @PostMapping
    public ResponseEntity<Evento> crear(
            @Valid @RequestBody Evento evento) {
        Evento nuevo = eventoService.crear(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar evento
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.actualizar(id, evento));
    }

    // Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}