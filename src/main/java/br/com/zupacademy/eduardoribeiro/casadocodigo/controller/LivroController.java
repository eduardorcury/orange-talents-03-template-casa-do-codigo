package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.DetalheLivro;
import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.ItemLivroResponse;
import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoLivro;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<ItemLivroResponse>> listar() {

        List<Livro> livros = entityManager.createQuery("from Livro").getResultList();

        List<ItemLivroResponse> lista = livros.stream().map(livro ->
                new ItemLivroResponse(livro.getId(), livro.getTitulo()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(lista);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheLivro> detalhar(@PathVariable("id") Long id) {

        Livro livro = entityManager.find(Livro.class, id);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        DetalheLivro detalheLivro = new DetalheLivro(livro);
        return ResponseEntity.ok(detalheLivro);

    }

}
