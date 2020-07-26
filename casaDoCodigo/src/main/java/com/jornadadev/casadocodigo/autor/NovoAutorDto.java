package com.jornadadev.casadocodigo.autor;

import com.jornadadev.casadocodigo.config.UniqueValue;
import com.jornadadev.casadocodigo.entity.Autor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class NovoAutorDto {

    @Email
    @NotBlank
    @UniqueValue(fieldName = "email", domainClass = Autor.class)
    private String email;

    @NotBlank
    private String nome;

    @Size(max = 400)
    @NotBlank
    private String descricao;

    private LocalDateTime dataCadastramento = LocalDateTime.now();

    public Autor toModel() {
        Autor autor = new Autor(this.email, this.nome, this.descricao, dataCadastramento);
        return autor;
    }
}
