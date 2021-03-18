package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoAutor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Autor;
import br.com.zupacademy.eduardoribeiro.casadocodigo.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailUnicoValidator implements Validator {

    private final AutorRepository autorRepository;

    public EmailUnicoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutor.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovoAutor novoAutor = (NovoAutor) target;
        Optional<Autor> autorOptional = autorRepository.findByEmail(novoAutor.getEmail());
        if (autorOptional.isPresent()) {
            errors.rejectValue("email", null, "Email já cadastrado");
        }

    }
}
