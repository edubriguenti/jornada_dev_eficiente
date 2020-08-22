package com.jornadadev.mercadolivre.config;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

   private String domainAttribute;
   private Class<?> aClass;
   private final EntityManager em;

   public void initialize(UniqueValue constraint) {
      domainAttribute = constraint.fieldName();
      aClass = constraint.domainClass();
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      Query query = em.createQuery("select 1 from " + aClass.getName() + " where " +
                                   domainAttribute + " =:value");
      query.setParameter("value", obj);
      List<?> list = query.getResultList();
      Assert.state(list.size() <= 1, "Foi encontrado mais de um "+aClass+" com atributo = " +obj);
      return list.isEmpty();
   }
}
