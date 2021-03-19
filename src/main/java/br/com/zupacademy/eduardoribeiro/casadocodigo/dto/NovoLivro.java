package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;


import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Livro;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.ExisteEntidade;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.Unico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivro {

    @NotBlank
    @Unico(campo = "titulo", classe = Livro.class)
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private int numeroPaginas;

    @NotBlank
    @Unico(campo = "isbn", classe = Livro.class)
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING )
    private LocalDate dataPublicacao;

    @NotNull
    @ExisteEntidade(entidade = Categoria.class)
    private Long categoriaId;

    @NotNull
    @ExisteEntidade(entidade = Autor.class)
    private Long autorId;

    @JsonCreator
    public NovoLivro(@NotBlank String titulo,
                     @NotBlank @Length(max = 500) String resumo,
                     @NotBlank String sumario,
                     @NotNull @Min(20) BigDecimal preco,
                     @NotNull @Min(100) int numeroPaginas,
                     @NotBlank String isbn,
                     @NotNull @Future @JsonProperty("dataPublicacao") LocalDate dataPublicacao,
                     @NotNull Long categoriaId,
                     @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro converterParaModel(EntityManager entityManager) {

        Categoria categoria = entityManager.find(Categoria.class, categoriaId);
        Autor autor = entityManager.find(Autor.class, autorId);

        Assert.state(categoria != null && autor != null, "Categoria e/ou autor inexistente(s)");

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco,
                    this.numeroPaginas, this.isbn, this.dataPublicacao, categoria, autor);

    }

}
