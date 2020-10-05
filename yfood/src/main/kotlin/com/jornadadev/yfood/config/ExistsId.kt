package com.jornadadev.yfood.config

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [ExistsIdValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(RetentionPolicy.RUNTIME)
annotation class ExistsId(
        val message: String = "{idNaoEncontrado}",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<Any>> = [],
        val fieldName: String,
        val domainClass: KClass<*>
)