package com.jornadadev.mercadolivre.entity;

import com.jornadadev.mercadolivre.cadastro.produto.CaracteristicaRequest;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @EqualsAndHashCode.Include
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @NotBlank
    private String descricao;
    @ManyToOne
    @NotNull
    @Valid
    private Categoria categoria;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao,
                   Categoria categoria, Usuario dono,
                   List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristicaRequest -> caracteristicaRequest.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "O minimo de caracteristicas são 3.");
    }

    @Deprecated
    protected Produto(){

    }

    public void associaImagens(Set<String> links) {
        final Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        return this.dono.equals(usuario);
    }
}
