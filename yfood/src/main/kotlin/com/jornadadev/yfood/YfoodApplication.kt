package com.jornadadev.yfood

import com.jornadadev.yfood.entities.FormasPagamentoEnum
import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootApplication
class YfoodApplication : CommandLineRunner {

    @PersistenceContext
    lateinit var em: EntityManager

    @Transactional
    override fun run(vararg args: String?) {
        val seya = Usuario(
                email = "seya@gmail.com",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.VISA, FormasPagamentoEnum.MASTERCARD)
        )
        val shiriu = Usuario(
                email = "shiriu@gmail.com",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.VISA, FormasPagamentoEnum.ELO, FormasPagamentoEnum.DINHEIRO)
        )
        val shun = Usuario(
                email = "shun@gmail.com",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.MASTERCARD, FormasPagamentoEnum.MAQUINA)
        )
        val iki = Usuario(
                email = "iki@gmail.com",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.DINHEIRO)
        )
        val hyoga = Usuario(
                email = "hyoga@gmail.com",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.VISA, FormasPagamentoEnum.ELO)
        )

        em.persist(seya)
        em.persist(shiriu)
        em.persist(shun)
        em.persist(iki)
        em.persist(hyoga)

        val sagaSantuario = Restaurante(
                nome = "Saga Santuario",
                formasPagamentoEnum = setOf(
                        FormasPagamentoEnum.VISA,
                        FormasPagamentoEnum.MASTERCARD,
                        FormasPagamentoEnum.ELO,
                        FormasPagamentoEnum.DINHEIRO
                )
        )

        val sagaGuerreirosDeuses = Restaurante(
                nome = "Saga Guerreiros Deuses",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.VISA, FormasPagamentoEnum.MASTERCARD, FormasPagamentoEnum.ELO)
        )

        val sagaPoseydon = Restaurante(
                nome = "Saga Poseydon",
                formasPagamentoEnum = setOf(FormasPagamentoEnum.VISA, FormasPagamentoEnum.MASTERCARD, FormasPagamentoEnum.ELO)
        )
        em.persist(sagaSantuario)
        em.persist(sagaGuerreirosDeuses)
        em.persist(sagaPoseydon)
    }
}

fun main(args: Array<String>) {
    runApplication<YfoodApplication>(*args)
}
