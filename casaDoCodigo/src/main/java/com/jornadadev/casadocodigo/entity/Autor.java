package com.jornadadev.casadocodigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    private String nome;

    @Size(max = 400)
    @NotBlank
    private String descricao;

    private LocalDateTime dataCadastramento;

    public Autor(String email, String nome, String descricao, LocalDateTime dataCadastramento) {
        this.email = email;
        this.descricao = descricao;
        this.nome = nome;
        this.dataCadastramento = dataCadastramento;
    }


}
