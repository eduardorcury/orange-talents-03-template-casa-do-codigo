package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoria {

    @NotBlank
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
