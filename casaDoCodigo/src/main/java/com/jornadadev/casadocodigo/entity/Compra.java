package com.jornadadev.casadocodigo.entity;

import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@ToString
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @OneToOne
    private CupomDesconto cupomDesconto;

    public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
                  @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                  @NotBlank String cidade, @NotNull Pais pais, @NotBlank String telefone,
                  @NotBlank String cep, Function<Compra, Pedido> funcaoCriacaoDePedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoDePedido.apply(this);
    }

    public void aplicarDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não podemos associar um estado caso o país seja Nulo.");
        Assert.isTrue(estado.pertence(pais), "Este estado não pertence ao país.");
        this.estado = estado;
    }

    @Deprecated
    protected Compra() {

    }
}
