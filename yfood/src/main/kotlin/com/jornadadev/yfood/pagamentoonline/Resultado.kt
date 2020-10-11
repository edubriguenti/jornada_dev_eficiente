package com.jornadadev.yfood.pagamentoonline

import org.springframework.util.Assert
import java.util.*

/**
 * Indica o resultado de uma operação. Inspirado no Either do Scala.
 * @author albertoluizsouza
 *
 * @param <E> Tipo que representa o erro
 * @param <S> Tipo que representa o sucesso
</S></E> */
class Resultado<E : Exception?, S> private constructor() {
    private var erro: E? = null
    private var sucesso: S? = null
    fun temErro(): Boolean {
        return erro != null
    }

    val exception: E?
        get() {
            Assert.isNull(sucesso, "Não pode ter sucesso para ter erro")
            Assert.isTrue(temErro(), "Você só deveria buscar por quando tiver dado erro")
            return erro
        }

    /**
     *
     * @return stackTrace como uma String
     */
    val stackTrace: String
        get() {
            Assert.isNull(sucesso, "Não pode ter sucesso para ter erro")
            Assert.isTrue(temErro(), "Você só deveria buscar por quando tiver dado erro")
            return Arrays.toString(erro!!.stackTrace)
        }

    fun get(): S? {
        Assert.isTrue(!temErro(), "Não pode ter tido erro para ter tido sucesso")
        return sucesso
    }

    companion object {
        fun <T> sucesso(objeto: T): Resultado<Exception, T> {
            val resultado = Resultado<Exception, T>()
            resultado.sucesso = objeto
            return resultado
        }

        fun <E : Exception?, T> erro(exception: E): Resultado<E, T> {
            val resultado = Resultado<E, T>()
            resultado.erro = exception
            return resultado
        }
    }
}