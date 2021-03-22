package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;


import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Cliente;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Pais;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.CpfOuCnpj;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.ExisteEntidade;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoCliente {

    @NotBlank
    @Email
    @Unico(campo = "email", classe = Cliente.class)
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CpfOuCnpj
    @Unico(campo = "documento", classe = Cliente.class)
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExisteEntidade(entidade = Pais.class)
    private Long paisId;

    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public NovoCliente(@NotBlank @Email String email,
                       @NotBlank String nome,
                       @NotBlank String sobrenome,
                       @NotBlank @CpfOuCnpj String documento,
                       @NotBlank String endereco,
                       @NotBlank String complemento,
                       @NotBlank String cidade,
                       @NotNull Long paisId,
                       Long estadoId,
                       @NotBlank String telefone,
                       @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente converterParaModel(EntityManager entityManager) {

        Pais pais = entityManager.find(Pais.class, paisId);
        Assert.state(pais != null, "País inexistente");

        Cliente cliente = new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco,
                this.complemento, this.cidade, pais, this.telefone, this.cep);

        if (pais.possuiEstados()) {
            Estado estado = entityManager.find(Estado.class, estadoId);
            Assert.state(estado != null, "Estado inexistente");
            cliente.setEstado(estado);
        }

        return cliente;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

}
