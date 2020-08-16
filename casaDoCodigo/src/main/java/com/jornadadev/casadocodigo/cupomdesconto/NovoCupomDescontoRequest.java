package com.jornadadev.casadocodigo.cupomdesconto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.CupomDesconto;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@ToString
@Getter
public class NovoCupomDescontoRequest {
    @NotEmpty
    @UniqueValue(domainClass = CupomDesconto.class, fieldName = "codigo")
    private String codigo;

    @NotNull
    @Positive
    @Max(value = 100)
    private Integer percentual;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    public CupomDesconto toModel() {
        CupomDesconto novoCupom = new CupomDesconto(codigo, percentual, validade);
        return novoCupom;
    }
}
