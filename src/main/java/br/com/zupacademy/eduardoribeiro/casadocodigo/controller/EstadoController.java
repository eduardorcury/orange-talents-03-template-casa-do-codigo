package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoEstado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.EstadoUnicoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @PersistenceContext
    private EntityManager entityManager;

    private final EstadoUnicoValidator estadoUnicoValidator;

    public EstadoController(EstadoUnicoValidator estadoUnicoValidator) {
        this.estadoUnicoValidator = estadoUnicoValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoUnicoValidator);
    }

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoEstado estado) {

        Estado model = estado.converterParaModel(entityManager);
        entityManager.persist(model);
        return ResponseEntity.ok().build();

    }

}
