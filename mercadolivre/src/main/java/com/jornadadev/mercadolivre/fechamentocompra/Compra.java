package com.jornadadev.mercadolivre.fechamentocompra;

import com.jornadadev.mercadolivre.entity.Produto;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.TreeSet;

@Entity
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Positive
    @NotNull
    private Integer quantidade;
    @ManyToOne
    @NotNull
    private Produto produtoEscolhido;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Set<Transacao> transacoes = new TreeSet<>();

    public Compra(Produto produtoASerComprado, Integer quantidade, Usuario comprador, @NotNull GatewayPagamento gateway) {
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Compra() {

    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        final Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transacao igual a essa processada: ".concat(novaTransacao.toString()));
        final long transacoesComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).count();
        Assert.isTrue(transacoesComSucesso == 0, "Essa compra já foi concluída com sucesso.");
        this.transacoes.add(novaTransacao);
    }
}
