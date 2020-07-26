package com.jornadadev.casadocodigo.repository;

import com.jornadadev.casadocodigo.entity.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {
    List<Livro> findAll();
}
