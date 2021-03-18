package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoAutor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.EmailUnicoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;

    private final EmailUnicoValidator emailUnicoValidator;

    public AutorController(EmailUnicoValidator emailUnicoValidator) {
        this.emailUnicoValidator = emailUnicoValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailUnicoValidator);
    }

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutor autor) {
        Autor model = autor.converterParaModel();
        entityManager.persist(model);
        return ResponseEntity.ok().build();
    }

}
