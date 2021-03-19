package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovaCategoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.NomeCategoriaUnicoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    private final NomeCategoriaUnicoValidator validator;

    public CategoriaController(NomeCategoriaUnicoValidator validator) {
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoria categoria) {
        System.out.println(categoria);
        Categoria model = categoria.converterParaModel();
        entityManager.persist(model);
        return ResponseEntity.ok().build();
    }

}
