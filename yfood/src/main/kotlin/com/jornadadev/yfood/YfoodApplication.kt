package com.jornadadev.yfood

import com.jornadadev.yfood.entities.FormaPagamentoEnum
import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootApplication
@EnableFeignClients
class YfoodApplication : CommandLineRunner {

    @PersistenceContext
    lateinit var em: EntityManager

    @Transactional
    override fun run(vararg args: String?) {
        val seya = Usuario(
                email = "seya@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD)
        )
        val shiriu = Usuario(
                email = "shiriu@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.ELO, FormaPagamentoEnum.DINHEIRO)
        )
        val shun = Usuario(
                email = "shun@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.MASTERCARD, FormaPagamentoEnum.MAQUINA)
        )
        val iki = Usuario(
                email = "iki@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.DINHEIRO)
        )
        val hyoga = Usuario(
                email = "hyoga@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.ELO)
        )

        em.persist(seya)
        em.persist(shiriu)
        em.persist(shun)
        em.persist(iki)
        em.persist(hyoga)

        val sagaSantuario = Restaurante(
                nome = "Saga Santuario",
                formaPagamentoEnum = setOf(
                        FormaPagamentoEnum.VISA,
                        FormaPagamentoEnum.MASTERCARD,
                        FormaPagamentoEnum.ELO,
                        FormaPagamentoEnum.DINHEIRO
                )
        )

        val sagaGuerreirosDeuses = Restaurante(
                nome = "Saga Guerreiros Deuses",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD, FormaPagamentoEnum.ELO)
        )

        val sagaPoseydon = Restaurante(
                nome = "Saga Poseydon",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD, FormaPagamentoEnum.ELO)
        )
        em.persist(sagaSantuario)
        em.persist(sagaGuerreirosDeuses)
        em.persist(sagaPoseydon)
    }
}

fun main(args: Array<String>) {
    runApplication<YfoodApplication>(*args)
}
