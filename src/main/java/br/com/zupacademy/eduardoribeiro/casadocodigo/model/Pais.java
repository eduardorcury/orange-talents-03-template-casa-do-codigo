package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<Estado> estados;

    @Deprecated
    public Pais() {

    }

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    public boolean possuiEstados() {
        return !this.estados.isEmpty();
    }

    public List<Estado> getEstados() {
        return estados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(id, pais.id) && Objects.equals(nome, pais.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
