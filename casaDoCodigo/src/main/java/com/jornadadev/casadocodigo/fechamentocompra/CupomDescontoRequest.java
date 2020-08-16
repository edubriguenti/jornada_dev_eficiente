package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.config.ExistsId;
import com.jornadadev.casadocodigo.entity.CupomDesconto;
import com.jornadadev.casadocodigo.repository.CupomDescontoRepository;

public class CupomDescontoRequest {

    @ExistsId(domainClass = CupomDesconto.class, fieldName = "codigo")
    private String codigo;

    public CupomDesconto toModel(CupomDescontoRepository repository) {
        return repository.findByCodigo(this.codigo);
    }

}
