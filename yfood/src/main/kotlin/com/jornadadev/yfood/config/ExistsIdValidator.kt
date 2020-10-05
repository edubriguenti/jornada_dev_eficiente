package com.jornadadev.yfood.config

import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class ExistsIdValidator (private val em: EntityManager) : ConstraintValidator<ExistsId, Any?> {
    private lateinit var domainAttribute: String
    private lateinit var aClass: KClass<*>
    override fun initialize(constraintAnnotation: ExistsId) {
        domainAttribute = constraintAnnotation.fieldName
        aClass = constraintAnnotation.domainClass
    }

    override fun isValid(valor: Any?, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        if (valor == null) return true
        val query = em.createQuery("select 1 from " + aClass.simpleName + " where " +
                domainAttribute + " =:value ")
        query.setParameter("value", valor)
        val list = query.resultList
        return list.isNotEmpty()
    }
}