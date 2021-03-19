package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;

import javax.validation.constraints.NotBlank;

public class NovaCategoria {

    @NotBlank
    @Unico(campo = "nome", classe = Categoria.class)
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria converterParaModel() {
        return new Categoria(this.nome);
    }

}
