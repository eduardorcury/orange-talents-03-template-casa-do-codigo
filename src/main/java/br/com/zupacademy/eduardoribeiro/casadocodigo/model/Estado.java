package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Valid
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return id.equals(estado.id) && nome.equals(estado.nome) && pais.equals(estado.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pais);
    }
}
