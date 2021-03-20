package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Livro;
import org.springframework.util.Assert;

import java.math.BigDecimal;

public class DetalheLivro {

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private int numeroPaginas;

    private String isbn;

    private String nomeAutor;

    private String descricaoAutor;

    public DetalheLivro(Livro livro) {

        Assert.state(livro != null, "Livro não encontrado");

        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.isbn = livro.getIsbn();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.nomeAutor = livro.getNomeAutor();
        this.descricaoAutor = livro.getDescricaoAutor();

    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }
}
