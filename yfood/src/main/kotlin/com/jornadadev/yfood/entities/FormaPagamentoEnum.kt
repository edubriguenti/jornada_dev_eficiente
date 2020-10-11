package com.jornadadev.yfood.entities

enum class FormaPagamentoEnum(val online: Boolean, val descricao: String) {

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

    open fun pertence(vararg grupo: FormaPagamentoEnum): Boolean {
        return grupo.any { forma -> forma == this }
    }
}
