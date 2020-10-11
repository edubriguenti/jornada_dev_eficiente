package com.jornadadev.yfood.entities

import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
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
        val formaPagamentoEnum: Set<FormaPagamentoEnum>
) {
    init {
        println("Criando objeto Usuario")
        Assert.hasText(email, "Email não pode ser branco.")
        Assert.isTrue(formaPagamentoEnum.isNotEmpty(), "Tipos de pagamento não pode ser vazio.")
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    fun filtraFormasPagamento(restaurante: Restaurante, regrasFraude: Set<RegraFraude>) : Set<FormaPagamentoEnum> {
        return this.formaPagamentoEnum
                .filter { restaurante.aceita(it) }
                .filter { formaPagamento ->
                    regrasFraude.all {
                        it.aceita(this, formaPagamento)
                    }
                }
                .toSet()
    }

    fun podePagar(
            restaurante: Restaurante,
            formaPagamento: FormaPagamentoEnum,
            regrasFraude: Set<RegraFraude>
    ) : Boolean = filtraFormasPagamento(restaurante, regrasFraude).contains(formaPagamento)



}
