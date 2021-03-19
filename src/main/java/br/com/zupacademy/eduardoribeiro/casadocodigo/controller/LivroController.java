package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoAutor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoLivro;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoLivro livro) {
        Livro model = livro.converterParaModel(entityManager);
        entityManager.persist(model);
        return ResponseEntity.ok().build();
    }

}
