package com.jornadadev.yfood.outrossistemas

import com.jornadadev.yfood.entities.Pagamento
import org.hibernate.validator.constraints.CreditCardNumber
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class DadosCompraGenerico {
    var num_cartao: @CreditCardNumber String?
        private set
    var codigo_seguranca: @Min(100) @Max(999) Int
        private set
    var valor_compra: @Positive @NotNull BigDecimal?
        private set

    constructor(num_cartao: @CreditCardNumber String?,
                codigo_seguranca: @Min(100) @Max(999) Int,
                valor_compra: @Positive BigDecimal?) : super() {
        this.num_cartao = num_cartao
        this.codigo_seguranca = codigo_seguranca
        this.valor_compra = valor_compra
    }

    constructor(pagamento: @NotNull @Valid Pagamento?) {
        val dadosCartao = pagamento!!.getDadosCartao()
        codigo_seguranca = dadosCartao!!.codigoSeguranca
        num_cartao = dadosCartao.numero
        valor_compra = pagamento.valor
    }

    override fun toString(): String {
        return ("DadosCompraSaori [num_cartao=" + num_cartao
                + ", codigo_seguranca=" + codigo_seguranca + ", valor_compra="
                + valor_compra + "]")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DadosCompraGenerico

        if (num_cartao != other.num_cartao) return false
        if (codigo_seguranca != other.codigo_seguranca) return false
        if (valor_compra != other.valor_compra) return false

        return true
    }

    override fun hashCode(): Int {
        var result = num_cartao?.hashCode() ?: 0
        result = 31 * result + codigo_seguranca
        result = 31 * result + (valor_compra?.hashCode() ?: 0)
        return result
    }


}