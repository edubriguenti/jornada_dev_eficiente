package com.jornadadev.yfood

import com.jornadadev.yfood.entities.FormasPagamento
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
                formasPagamento = setOf(FormasPagamento.VISA, FormasPagamento.MASTERCARD)
        )
        val shiriu = Usuario(
                email = "shiriu@gmail.com",
                formasPagamento = setOf(FormasPagamento.VISA, FormasPagamento.ELO, FormasPagamento.DINHEIRO)
        )
        val shun = Usuario(
                email = "shun@gmail.com",
                formasPagamento = setOf(FormasPagamento.MASTERCARD, FormasPagamento.MAQUINA)
        )
        val iki = Usuario(
                email = "iki@gmail.com",
                formasPagamento = setOf(FormasPagamento.DINHEIRO)
        )
        val hyoga = Usuario(
                email = "hyoga@gmail.com",
                formasPagamento = setOf(FormasPagamento.VISA, FormasPagamento.ELO)
        )

        em.persist(seya)
        em.persist(shiriu)
        em.persist(shun)
        em.persist(iki)
        em.persist(hyoga)

        val sagaSantuario = Restaurante(
                nome = "Saga Santuario",
                formasPagamento = setOf<FormasPagamento>(FormasPagamento.VISA, FormasPagamento.MASTERCARD, FormasPagamento.ELO)
        )

        val sagaGuerreirosDeuses = Restaurante(
                nome = "Saga Guerreiros Deuses",
                formasPagamento = setOf<FormasPagamento>(FormasPagamento.VISA, FormasPagamento.MASTERCARD, FormasPagamento.ELO)
        )

        val sagaPoseydon = Restaurante(
                nome = "Saga Poseydon",
                formasPagamento = setOf<FormasPagamento>(FormasPagamento.VISA, FormasPagamento.MASTERCARD, FormasPagamento.ELO)
        )
        em.persist(sagaGuerreirosDeuses)
        em.persist(sagaPoseydon)
        em.persist(sagaSantuario)
    }
}

fun main(args: Array<String>) {
    runApplication<YfoodApplication>(*args)
}
