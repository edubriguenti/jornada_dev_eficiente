package com.jornadadev.yfood.entities

import org.springframework.util.Assert
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Usuario(
        @NotBlank
        @NotNull
        @Email
        val email: String,
        @Size(min = 1)
        @ElementCollection
        @Enumerated(value = EnumType.STRING)
        val formasPagamento: Set<FormasPagamento>
) {
    init {
        println("Criando objeto Usuario")
        Assert.hasText(email, "Email não pode ser branco.")
        Assert.isTrue(formasPagamento.isNotEmpty(), "Tipos de pagamento não pode ser vazio.")
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    fun filtraFormasPagamento(restaurante: Restaurante): Set<FormasPagamento> =
            this.formasPagamento.filter { restaurante.formasPagamento.contains(it) }.toSet()

}
