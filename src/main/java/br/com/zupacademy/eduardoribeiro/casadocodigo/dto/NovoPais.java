package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Pais;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovoPais {

    @NotBlank
    @Unico(campo = "nome", classe = Pais.class)
    private String nome;

    @JsonCreator
    public NovoPais(@NotBlank @JsonProperty("nome") String nome) {
        this.nome = nome;
    }

    public Pais converterParaModel() {
        return new Pais(this.nome);
    }

}
