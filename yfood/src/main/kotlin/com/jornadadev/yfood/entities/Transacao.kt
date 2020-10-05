package com.jornadadev.yfood.entities

import java.util.*
import javax.persistence.Embeddable

@Embeddable
class Transacao(
        var status: StatusTransacao
) {
        var codigo: String = UUID.randomUUID().toString()
}