package com.jornadadev.yfood.entities

import com.fasterxml.jackson.databind.ObjectMapper
import com.jornadadev.yfood.entities.Transacao.Companion.concluida
import com.jornadadev.yfood.pagamentoonline.gateway.Gateway
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

@Embeddable
class Transacao {
    @NotNull
    private var statusTransacao: StatusTransacao? = null

    @NotBlank
    private var codigo: String? = null

    @NotNull
    private var instante: @PastOrPresent LocalDateTime? = null
    private var informacaoAdicional: String? = null

    constructor(statusTransacao: StatusTransacao) {
        this.statusTransacao = statusTransacao
        codigo = UUID.randomUUID().toString()
        instante = LocalDateTime.now()
    }

    override fun toString(): String {
        return ("Transacao [statusTransacao=" + statusTransacao + ", codigo="
                + codigo + "]")
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (codigo == null) 0 else codigo.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as Transacao
        if (codigo == null) {
            if (other.codigo != null) return false
        } else if (codigo != other.codigo) return false
        return true
    }

    fun temStatus(status: StatusTransacao): Boolean {
        return statusTransacao == status
    }

    fun setInfoAdicional(infoAdicional: Map<String, Any>?) {
        val mapper = ObjectMapper()
        informacaoAdicional = mapper.writeValueAsString(infoAdicional)
    }

    companion object {
        fun concluida(gateway: Gateway): Transacao {
            val mapper = ObjectMapper()
            val transacao = Transacao(StatusTransacao.CONCLUIDA)
            transacao.informacaoAdicional = mapper.writeValueAsString(mapOf("gateway" to gateway.toString()))
            return transacao
        }
    }
}