package com.jornadadev.yfood.outrossistemas

import com.jornadadev.yfood.pagamentoonline.DadosCartao
import org.hibernate.validator.constraints.CreditCardNumber
import java.math.BigDecimal
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Positive

class DadosCompraSeyaRequest {
    var num_cartao: @CreditCardNumber String? = null
        private set
    var codigo_seguranca = 0
        private set
    var valor_compra: @Positive BigDecimal? = null
        private set

    constructor(num_cartao: @CreditCardNumber String?,
                codigo_seguranca: @Min(100) @Max(999) Int,
                valor_compra: @Positive BigDecimal?) : super() {
        this.num_cartao = num_cartao
        this.codigo_seguranca = codigo_seguranca
        this.valor_compra = valor_compra
    }

    constructor(dadosCartao: DadosCartao, valor: BigDecimal?) {
        num_cartao = dadosCartao.numero
        codigo_seguranca = dadosCartao.codigoSeguranca
        valor_compra = valor
    }

    constructor() {
        // TODO Auto-generated constructor stub
    }

    override fun toString(): String {
        return ("DadosCompraSeyaRequest [num_cartao=" + num_cartao
                + ", codigo_seguranca=" + codigo_seguranca + ", valor_compra="
                + valor_compra + "]")
    }
}