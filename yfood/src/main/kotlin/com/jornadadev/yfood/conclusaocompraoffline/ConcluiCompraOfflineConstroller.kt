package com.jornadadev.yfood.conclusaocompraoffline

import com.jornadadev.yfood.repository.PagamentoRepository
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConcluiCompraOfflineConstroller(val pagamentoRepository: PagamentoRepository) {

    @PostMapping("/pagamento/offline/{codigoPagamento}/finaliza")
    @Transactional
    fun concluiPagamentoOffline(@PathVariable("codigoPagamento") codigoPagamento: String)
            : ResponseEntity<String> {
        val possivelPagamento = pagamentoRepository.findByCodigo(codigoPagamento)
        if (possivelPagamento.isEmpty) {
            return ResponseEntity.notFound().build()
        }

        val pagamento = possivelPagamento.get()
        if (pagamento.concluido()) {
            return ResponseEntity.badRequest().body("Pagamento já conclído")
        }
        pagamento.conclui()
        return ResponseEntity.ok().build()
    }

}