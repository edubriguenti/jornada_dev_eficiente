package com.jornadadev.casadocodigo.config;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class ExistsIdValidator implements ConstraintValidator<ExistsId, Integer> {

    private final EntityManager em;
    private String domainAttribute;
    private Class<?> aClass;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        aClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Integer valor, ConstraintValidatorContext constraintValidatorContext) {
        if (valor == null)
            return true;

        Query query = em.createQuery("select 1 from " + aClass.getName() + " where " +
                                     domainAttribute + " =:value ");
        query.setParameter("value", valor);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
