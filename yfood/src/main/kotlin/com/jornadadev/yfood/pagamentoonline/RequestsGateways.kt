package com.jornadadev.yfood.pagamentoonline

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(url = "\${enderecos-externos.gateways.base-url}", name = "gateways")
interface RequestsGateways {
//    @PostMapping("/seya/verifica")
//    fun seyaVerifica(request: DadosCartaoSeyaRequest?): Int
//
//    @PostMapping(value = ["/seya/processa/{codigo}"])
//    fun seyaProcessa(@PathVariable("codigo") codigo: Int?,
//                     request: DadosCompraSeyaRequest?)
//
//    @PostMapping(value = ["/saori/processa"])
//    fun saoriProcessa(request: DadosCompraGenerico?)
//
//    @PostMapping(value = ["/tango/processa"])
//    fun tangoProcessa(request: DadosCompraGenerico?)
}