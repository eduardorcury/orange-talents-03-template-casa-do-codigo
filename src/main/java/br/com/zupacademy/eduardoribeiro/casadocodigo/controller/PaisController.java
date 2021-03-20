package br.com.zupacademy.eduardoribeiro.casadocodigo.controller;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoPais;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Pais;
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
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(consumes = { "application/json" })
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoPais pais) {

        Pais model = pais.converterParaModel();
        entityManager.persist(model);
        return ResponseEntity.ok().build();

    }

}
