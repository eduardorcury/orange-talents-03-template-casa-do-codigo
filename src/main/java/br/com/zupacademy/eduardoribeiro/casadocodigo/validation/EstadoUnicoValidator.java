package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoEstado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EstadoUnicoValidator implements Validator {

    private final EstadoRepository estadoRepository;

    public EstadoUnicoValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoEstado.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NovoEstado estado = (NovoEstado) target;

        Optional<Estado> estadoOptional = estadoRepository
                .findByNomeAndPaisId(estado.getNome(), estado.getPaisId());

        estadoOptional.ifPresent(object -> errors.rejectValue("paisId", null, String.format(
                "Já existe um Estado com o nome %s no país de id %s", estado.getNome(), estado.getPaisId()
        )));
    }
}
