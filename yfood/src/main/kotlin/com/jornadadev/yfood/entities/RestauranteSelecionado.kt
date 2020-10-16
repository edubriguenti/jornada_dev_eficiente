package com.jornadadev.yfood.entities

import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Entity
class RestauranteSelecionado(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @ManyToOne
        @NotNull
        @Valid
        val usuario: Usuario,
        @NotNull
        @Valid
        @ManyToOne
        val restaurante: Restaurante
)
