package com.jornadadev.yfood.pagamentoonline

/**
 * Representa os dados de um cartão que pode ter sido salvo junto com um pagamento
 * @author albertoluizsouza
 */
class DadosCartao {
    var numero: String? = null
        private set
    var codigoSeguranca = 0
        private set

    constructor(numero: String?, codigoSeguranca: Int) : super() {
        this.numero = numero
        this.codigoSeguranca = codigoSeguranca
    }
}