package com.jornadadev.casadocodigo.livro;

import com.jornadadev.casadocodigo.entity.Livro;
import com.jornadadev.casadocodigo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/livro")
//6 pontos de carga intrínseca
public class LivroController {

    private final EntityManager em;
    private final LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarLivro(@Valid @RequestBody LivroDto livroDto) {
        System.out.println(livroDto);
        final Livro livro = livroDto.toModel(em);
        em.persist(livro);
        return ResponseEntity.ok().body(livro.toString());
    }

    @GetMapping
    public ResponseEntity<List<LivrosCadastradosDto>> obterLivrosCadastrados() {
        final List<Livro> livros = livroRepository.findAll();
        final List<LivrosCadastradosDto> livrosCadastrados = livros.stream().map
                (l -> new LivrosCadastradosDto(l.getId(), l.getTitulo())).collect(Collectors.toList());
        return ResponseEntity.ok().body(livrosCadastrados);
    }


}

