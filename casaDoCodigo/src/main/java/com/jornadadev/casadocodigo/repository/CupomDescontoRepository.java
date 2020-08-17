package com.jornadadev.casadocodigo.repository;

import com.jornadadev.casadocodigo.entity.CupomDesconto;
import com.jornadadev.casadocodigo.entity.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupomDescontoRepository extends CrudRepository<CupomDesconto, Long> {
    /**
     * Busca um cupom que existe.
     * @param codigo
     * @return
     */
    CupomDesconto getByCodigo(String codigo);
}
