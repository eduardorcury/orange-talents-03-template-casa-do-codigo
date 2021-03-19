package br.com.zupacademy.eduardoribeiro.casadocodigo.repository;

import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

}
