package com.jornadadev.casadocodigo.repository;

import com.jornadadev.casadocodigo.entity.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    Optional<Categoria> findByNome(String name);
}
