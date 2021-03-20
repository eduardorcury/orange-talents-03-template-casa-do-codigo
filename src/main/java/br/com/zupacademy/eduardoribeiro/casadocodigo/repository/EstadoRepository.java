package br.com.zupacademy.eduardoribeiro.casadocodigo.repository;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long> {

    Optional<Estado> findByNomeAndPaisId(String nome, Long paisId);

}
