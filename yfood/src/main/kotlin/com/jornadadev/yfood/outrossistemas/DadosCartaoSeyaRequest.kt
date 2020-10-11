package com.jornadadev.yfood.outrossistemas

import com.jornadadev.yfood.pagamentoonline.DadosCartao
import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Max
import javax.validation.constraints.Min

class DadosCartaoSeyaRequest {
    var num_cartao: @CreditCardNumber String?
        private set
    var codigo_seguranca: @Min(100) @Max(999) Int
        private set

    constructor(num_cartao: @CreditCardNumber String?,
                codigo_seguranca: @Min(100) @Max(999) Int) : super() {
        this.num_cartao = num_cartao
        this.codigo_seguranca = codigo_seguranca
    }

    constructor(dadosCartao: DadosCartao) {
        num_cartao = dadosCartao.numero
        codigo_seguranca = dadosCartao.codigoSeguranca
    }

    override fun toString(): String {
        return ("DadosCartaoSeyaRequest [num_cartao=" + num_cartao
                + ", codigo_seguranca=" + codigo_seguranca + "]")
    }
}