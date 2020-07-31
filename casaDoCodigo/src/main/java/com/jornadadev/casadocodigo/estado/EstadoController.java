package com.jornadadev.casadocodigo.estado;

import com.jornadadev.casadocodigo.entity.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarEstado(@Valid @RequestBody EstadoDto estadoDto){
        Estado estado = estadoDto.toModel(em);
        em.persist(estado);
        return ResponseEntity.accepted().body(estado.toString());
    }
}
