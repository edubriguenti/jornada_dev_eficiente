package com.jornadadev.yfood.entities

import org.springframework.util.Assert
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Restaurante(
        @NotBlank
        val nome: String,
        @NotNull
        @Size(min=1)
        @ElementCollection
        val formasPagamentoEnum: Set<FormasPagamentoEnum>
) {
    init {
        Assert.hasText(nome, "Nome do restaurante não pode ser vazio.")
        Assert.isTrue(formasPagamentoEnum.isNotEmpty(), "Formas de pagamento não pode ser vazio.")
        println("Objeto restaurante criado.")
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    fun aceita(formaPagamentoEnum: FormasPagamentoEnum) = this.formasPagamentoEnum.contains(formaPagamentoEnum)
}