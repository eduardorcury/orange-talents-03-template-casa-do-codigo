package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.CpfOuCnpj;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String sobrenome;

    @NotBlank
    @CpfOuCnpj
    @Column(nullable = false, unique = true)
    private String documento;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    @NotBlank
    @Column(nullable = false)
    private String complemento;

    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @Valid
    @ManyToOne(optional = false)
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank
    @Column(nullable = false)
    private String telefone;

    @NotBlank
    @Column(nullable = false)
    private String cep;

    @Deprecated
    public Cliente() {

    }

    public Cliente(@NotBlank @Email String email,
                   @NotBlank String nome,
                   @NotBlank String sobrenome,
                   @NotBlank @CpfOuCnpj String documento,
                   @NotBlank String endereco,
                   @NotBlank String complemento,
                   @NotBlank String cidade,
                   Pais pais,
                   @NotBlank String telefone,
                   @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
