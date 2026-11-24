package com.mar.aconteceapi.service;

import com.mar.aconteceapi.entity.Categoria;
import com.mar.aconteceapi.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoría no encontrada: " + id));
    }

    public Optional<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public List<Categoria> obtenerActivas() {
        return categoriaRepository.findByActivaTrue();
    }

    public List<Categoria> buscarConFiltros(String nombre, String descripcion, Boolean activa) {
        return categoriaRepository.buscarConFiltros(nombre, descripcion, activa);
    }

    @Transactional
    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria actualizar(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Categoría no encontrada: " + id);
        }

        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Categoría no encontrada: " + id);
        }

        categoriaRepository.deleteById(id);
    }
}