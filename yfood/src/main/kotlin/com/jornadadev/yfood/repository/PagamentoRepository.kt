package com.jornadadev.yfood.repository

import com.jornadadev.yfood.entities.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PagamentoRepository : JpaRepository<Pagamento, Long> {
    fun findByIdPedido(idPedido: Long) : Optional<Pagamento>
    fun findByCodigo(codigo: String) : Optional<Pagamento>
}