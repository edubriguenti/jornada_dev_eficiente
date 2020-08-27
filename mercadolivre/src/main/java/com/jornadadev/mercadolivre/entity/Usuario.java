package com.jornadadev.mercadolivre.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6)
    private String senha;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataCadastro;

    /**
     * @param email string no formato de email
     * @param senha string em texto limpo
     */
    public Usuario(String email, String senha) {
        Assert.isTrue(StringUtils.hasLength(email), "Email não pode ser em branco");
        Assert.isTrue(StringUtils.hasLength(senha), "Senha não pode ser em branco");
        Assert.isTrue(senha.length() >= 6, "Senha deve ter no mínimo 6 caracteres.");
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.dataCadastro = LocalDateTime.now();
    }

    @Deprecated
    public Usuario(){

    }
}
