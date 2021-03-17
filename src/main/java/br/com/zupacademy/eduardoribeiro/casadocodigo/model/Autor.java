package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Length(max = 400)
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @Deprecated
    public Autor() {

    }

    public Autor(@NotBlank String nome,
                 @NotBlank @Email String email,
                 @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
