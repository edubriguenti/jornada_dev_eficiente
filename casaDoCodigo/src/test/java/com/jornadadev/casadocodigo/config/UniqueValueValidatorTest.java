package com.jornadadev.casadocodigo.config;

import com.jornadadev.casadocodigo.entity.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniqueValueValidatorTest {

    @Mock
    private EntityManager em;

    @Mock
    private UniqueValue constraint;

    @Mock
    private Query query;

    @InjectMocks
    private UniqueValueValidator validator;

    @BeforeEach
    public void setup(){
        when(constraint.fieldName()).thenReturn("");
        Class autorClass = Autor.class;
        when(constraint.domainClass()).thenReturn(autorClass);
        when(em.createQuery(anyString())).thenReturn(query);
        validator.initialize(constraint);
    }

    @Test
    void isValid() {
        validator.isValid(null, null);
    }
}