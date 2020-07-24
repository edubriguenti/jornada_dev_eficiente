package com.jornadadev.casadocodigo.repository;

import com.jornadadev.casadocodigo.entity.Autor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Integer> {
    Optional<Autor> findByEmail(String email);
}
