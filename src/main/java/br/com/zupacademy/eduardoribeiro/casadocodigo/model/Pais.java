package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @Deprecated
    public Pais() {

    }

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }
}
