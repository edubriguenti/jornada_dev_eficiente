package com.jornadadev.casadocodigo.controller;

import com.jornadadev.casadocodigo.entity.Autor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AutorForm {

    @Email
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "Nome é obrigatório.")
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
