package com.jornadadev.yfood.pagamentoonline.gateway

import com.jornadadev.yfood.entities.FormaPagamentoEnum
import com.jornadadev.yfood.entities.Pagamento
import com.jornadadev.yfood.entities.Transacao
import com.jornadadev.yfood.outrossistemas.DadosCompraGenerico
import com.jornadadev.yfood.pagamentoonline.RequestsGateways
import com.jornadadev.yfood.pagamentoonline.Resultado
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service
class GatewaySaori(private val requestsGateways: RequestsGateways) : Gateway() {

    private val id: String = "gateway-saori"

    public override fun aceiteEspecifico(novoPagamentoSalvo: @Valid Pagamento): Boolean {
        val formaPagamento = novoPagamentoSalvo.formaPagamento
        return formaPagamento.pertence(FormaPagamentoEnum.VISA,
                FormaPagamentoEnum.MASTERCARD)
    }

    override fun processaEspecifico(
            pagamento: Pagamento): Resultado<Exception, Transacao> {
        println("Processando pagamento por gateway Saori")
        val request = DadosCompraGenerico(pagamento)
        requestsGateways.saoriProcessa(request)
        return Resultado.sucesso(Transacao.concluida(this))
    }

    override fun custoEspecifico(pagamento: Pagamento): BigDecimal {
        return pagamento.valor.multiply(BigDecimal("0.05"))
    }

    override fun toString(): String {
        return "GatewaySaori []"
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (id?.hashCode() ?: 0)
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as GatewaySaori
        if (id == null) {
            if (other.id != null) return false
        } else if (id != other.id) return false
        return true
    }
}