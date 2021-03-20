package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Pais;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.ExisteEntidade;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstado {

    @NotBlank
    private String nome;

    @NotNull
    @ExisteEntidade(entidade = Pais.class)
    private Long paisId;

    public NovoEstado(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public Estado converterParaModel(EntityManager entityManager) {

        Pais pais = entityManager.find(Pais.class, this.paisId);
        Assert.state(pais != null, "País inexistente");

        return new Estado(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }
}
