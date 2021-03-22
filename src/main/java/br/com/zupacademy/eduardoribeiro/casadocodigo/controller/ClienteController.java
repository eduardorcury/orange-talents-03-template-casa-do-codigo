package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoCliente;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Cliente;
import br.com.zupacademy.eduardoribeiro.casadocodigo.validation.EstadoPertenceAoPaisValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    private final EstadoPertenceAoPaisValidator estadoPertenceAoPaisValidator;

    public ClienteController(EstadoPertenceAoPaisValidator estadoPertenceAoPaisValidator) {
        this.estadoPertenceAoPaisValidator = estadoPertenceAoPaisValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPertenceAoPaisValidator);
    }

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid NovoCliente cliente) {

        Cliente model = cliente.converterParaModel(entityManager);
        entityManager.persist(model);
        return ResponseEntity.ok(model.getId());

    }

}
