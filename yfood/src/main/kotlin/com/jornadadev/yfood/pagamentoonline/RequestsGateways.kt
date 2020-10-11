package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.outrossistemas.DadosCartaoSeyaRequest
import com.jornadadev.yfood.outrossistemas.DadosCompraGenerico
import com.jornadadev.yfood.outrossistemas.DadosCompraSeyaRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(url = "\${enderecos-externos.gateways.base-url}", name = "gateways")
@Service
interface RequestsGateways {
    @PostMapping("/seya/verifica")
    fun seyaVerifica(request: DadosCartaoSeyaRequest?): Int

    @PostMapping(value = ["/seya/processa/{codigo}"])
    fun seyaProcessa(@PathVariable("codigo") codigo: Int?,
                     request: DadosCompraSeyaRequest?)

    @PostMapping(value = ["/saori/processa"])
    fun saoriProcessa(request: DadosCompraGenerico?)

    @PostMapping(value = ["/tango/processa"])
    fun tangoProcessa(request: DadosCompraGenerico?)
}