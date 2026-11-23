package com.mar.aconteceapi.controller;

import com.mar.aconteceapi.entity.Categoria;
import com.mar.aconteceapi.service.CategoriaService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    // Buscar categoría por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<Categoria> buscarPorNombre(
            @RequestParam String nombre) {
        return ResponseEntity.of(categoriaService.buscarPorNombre(nombre));
    }

    // Obtener categorías activas
    @GetMapping("/activas")
    public ResponseEntity<List<Categoria>> obtenerActivas() {
        return ResponseEntity.ok(categoriaService.obtenerActivas());
    }

    // Buscar categorías combinando hasta tres filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarConFiltros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Boolean activa) {
        return ResponseEntity.ok(
                categoriaService.buscarConFiltros(nombre, descripcion, activa));
    }

    // Crear categoría
    @PostMapping
    public ResponseEntity<Categoria> crear(
            @Valid @RequestBody Categoria categoria) {
        Categoria nueva = categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}