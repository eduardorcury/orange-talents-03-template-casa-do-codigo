package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoCliente;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class EstadoPertenceAoPaisValidator implements Validator {

    private final EstadoRepository estadoRepository;

    public EstadoPertenceAoPaisValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoCliente.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NovoCliente novoCliente = (NovoCliente) target;

        List<Estado> estadoList = estadoRepository.findByPaisId(novoCliente.getPaisId());

        if (!estadoList.isEmpty()) {
            if (novoCliente.getEstadoId() == null || estadoRepository.findById(novoCliente.getEstadoId()).isEmpty()) {
                errors.rejectValue("estadoId", null, "Estado não encontrado");
            } else {
                boolean valido = estadoList.stream()
                        .anyMatch(estado -> estado == estadoRepository.findById(novoCliente.getEstadoId()).get());
                if (!valido) {
                    errors.rejectValue("estadoId", null,"Estado não pertence ao país informado");
                }
            }
        }
    }
}
