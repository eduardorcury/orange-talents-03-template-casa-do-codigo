package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @ManyToOne(optional = false)
    private Pais pais;

    @Deprecated
    public Estado() {

    }

    public Estado(@NotBlank String nome,
                  @NotNull Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }
}
