package br.com.zupacademy.eduardoribeiro.casadocodigo.dto;

public class ItemLivroResponse {

    private Long id;
    private String titulo;

    public ItemLivroResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

}
