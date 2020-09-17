package com.jornadadev.mercadolivre.detalheproduto;

import com.jornadadev.mercadolivre.entity.Opiniao;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Isola as operações sobre um conjunto de opiniões.
 */
public class Opinioes {
    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public Double media() {
        final Set<Integer> notas = this.mapeiaOpinioes(opiniao -> opiniao.getNota());
        final OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }

    public int total() {
        return this.opinioes.size();
    }
}
