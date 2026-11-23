package com.mar.aconteceapi.controller;

import com.mar.aconteceapi.entity.Usuario;
import com.mar.aconteceapi.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // Buscar usuario por email
    @GetMapping("/buscar/email")
    public ResponseEntity<Usuario> buscarPorEmail(
            @RequestParam String email) {
        return ResponseEntity.of(usuarioService.buscarPorEmail(email));
    }

    // Buscar usuario por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<Usuario> buscarPorNombre(
            @RequestParam String nombre) {
        return ResponseEntity.of(usuarioService.buscarPorNombre(nombre));
    }

    // Buscar usuarios combinando hasta tres filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarConFiltros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) Boolean activo) {

        return ResponseEntity.ok(
                usuarioService.buscarConFiltros(nombre, apellidos, activo));
    }

    // Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> crear(
            @Valid @RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}