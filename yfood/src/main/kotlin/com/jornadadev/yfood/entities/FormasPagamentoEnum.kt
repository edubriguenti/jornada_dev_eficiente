package com.jornadadev.yfood.entities

enum class FormasPagamentoEnum(val online: Boolean, val descricao: String) {

    VISA(
            online = true,
            descricao = "cartão"
    ),
    MASTERCARD(
            true,
            "cartão"
    ),
    ELO(
            true,
            "cartão"
    ),
    HIBERCARD(
            true,
            "cartão"
    ),
    MAQUINA(
            false,
            "maquineta"
    ),
    DINHEIRO(
            false,
            "cash"
    );
}
