package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.config.ExistsId;
import com.jornadadev.casadocodigo.entity.Compra;
import com.jornadadev.casadocodigo.entity.CupomDesconto;
import com.jornadadev.casadocodigo.entity.Estado;
import com.jornadadev.casadocodigo.entity.Pais;
import com.jornadadev.casadocodigo.entity.Pedido;
import com.jornadadev.casadocodigo.repository.CupomDescontoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@ToString
@AllArgsConstructor
public class NovaCompraRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @Getter
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    @Getter
    private Integer pais;
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    @Getter
    private Integer estado;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;
    @Valid
    @NotNull
    @Getter
    private NovoPedidoRequest pedido;

    @ExistsId(domainClass = CupomDesconto.class, fieldName = "codigo")
    private String codigoCupom;

    public Compra toModel(EntityManager em, CupomDescontoRepository cupomDescontoRepository) {
        @NotNull final Pais pais = em.find(Pais.class, this.pais);
        final Function<Compra, Pedido> funcaoCriacaoDePedido = this.pedido.toModel(em);
        final Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais,
                telefone, cep, funcaoCriacaoDePedido);
        if (this.estado != null) {
            compra.setEstado(em.find(Estado.class, this.estado));
        }
        if (StringUtils.hasText(codigoCupom)) {
            compra.aplicaCupomDesconto(cupomDescontoRepository.getByCodigo(codigoCupom));
        }
        return compra;
    }

    public boolean documentoValido() {
        Assert.hasLength(documento, "Você não deveria validar o documento se ele não está preenchido.");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(this.documento, null) || cnpjValidator.isValid(this.documento, null);
    }

    public boolean temEstado() {
        return estado != null;
    }

    public Optional<String> getCupomDesconto(){
        return Optional.ofNullable(codigoCupom);
    }

}
