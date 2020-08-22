package com.jornadadev.mercadolivre.cadastro;

import com.jornadadev.mercadolivre.config.UniqueValue;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CadastroUsuarioRequest {
    @Email
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "email" )
    private String email;

    @NotBlank
    @Size(min=6)
    private String senha;


    public Usuario toModel() {
        return new Usuario(this.email, this.senha);
    }
}
