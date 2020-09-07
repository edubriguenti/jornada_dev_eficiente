package com.jornadadev.mercadolivre.entity;

import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@ToString
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    @ToString.Exclude
    private Produto produto;

    @URL
    @NotBlank
    private String link;

    public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public ImagemProduto() {

    }
}
