package com.jornadadev.mercadolivre.entity;

import com.jornadadev.mercadolivre.cadastro.produto.CaracteristicaRequest;
import com.jornadadev.mercadolivre.detalheproduto.DetalheProdutoOpiniao;
import com.jornadadev.mercadolivre.detalheproduto.Opinioes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
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
    @Getter
    private String nome;
    @Positive
    @NotNull
    @Getter
    private BigDecimal valor;
    @PositiveOrZero
    @NotNull
    @Getter
    private Integer quantidade;
    @NotBlank
    @Getter
    private String descricao;
    @ManyToOne
    @NotNull
    @Valid
    @Getter
    private Categoria categoria;
    @ManyToOne
    @NotNull
    @Valid
    @Getter
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    @ToString.Exclude
    @Getter
    private Set<Pergunta> perguntas = new TreeSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();

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


    public <T> List<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public <T> List<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public <T extends Comparable<T>> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public boolean abateEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior do que zero para abater o estoque.");
        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }
}
