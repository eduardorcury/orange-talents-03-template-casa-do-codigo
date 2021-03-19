package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovaCategoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoAutor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardoribeiro.casadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NomeCategoriaUnicoValidator implements Validator {

    private final CategoriaRepository categoriaRepository;

    public NomeCategoriaUnicoValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoria.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovaCategoria novaCategoria = (NovaCategoria) target;
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(novaCategoria.getNome());
        if (categoriaOptional.isPresent()) {
            errors.rejectValue("nome", null, "Nome já cadastrado");
        }

    }
}
