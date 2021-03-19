package br.com.zupacademy.eduardoribeiro.casadocodigo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String titulo;

    @NotBlank
    @Length(max = 500)
    @Column(nullable = false, length = 500)
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String sumario;

    @NotNull
    @Min(20)
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    @Column(nullable = false)
    private int numeroPaginas;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull
    @Future
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate dataPublicacao;

    @Valid
    @ManyToOne(optional = false)
    private Categoria categoria;

    @Valid
    @ManyToOne(optional = false)
    private Autor autor;

    @Deprecated
    public Livro() {

    }

    public Livro(@NotBlank String titulo,
                 @NotBlank @Length(max = 500) String resumo,
                 @NotBlank String sumario,
                 @NotNull @Min(20) BigDecimal preco,
                 @NotNull @Min(100) int numeroPaginas,
                 @NotBlank String isbn,
                 @NotNull @Future LocalDate dataPublicacao,
                 @Valid Categoria categoria,
                 @Valid Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }
}
