package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoAutor {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Unico(campo = "email", classe = Autor.class)
    private String email;

    @NotBlank
    @Length(max = 400)
    private String descricao;

    public NovoAutor(@NotBlank String nome,
                     @NotBlank @Email String email,
                     @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converterParaModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return email;
    }
}
