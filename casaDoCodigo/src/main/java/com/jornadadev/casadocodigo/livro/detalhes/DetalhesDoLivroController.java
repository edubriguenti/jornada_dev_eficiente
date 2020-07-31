package com.jornadadev.casadocodigo.livro.detalhes;

import com.jornadadev.casadocodigo.entity.Livro;
import com.jornadadev.casadocodigo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class DetalhesDoLivroController {

    private final LivroRepository livroRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DetalheLivroDto> obtemLivro(@PathVariable @Positive @Valid Integer id) {
        final Optional<Livro> possivelLivro = livroRepository.findById(id);
        if (!possivelLivro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        DetalheLivroDto detalheLivroDto = new DetalheLivroDto(possivelLivro.get());
        return ResponseEntity.ok().body(detalheLivroDto);
    }
}
