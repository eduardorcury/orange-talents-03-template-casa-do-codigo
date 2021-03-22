package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import br.com.zupacademy.eduardoribeiro.casadocodigo.dto.NovoCliente;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Estado;
import br.com.zupacademy.eduardoribeiro.casadocodigo.model.Pais;
import br.com.zupacademy.eduardoribeiro.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class EstadoPertenceAoPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

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

        if(errors.hasErrors()) {
            return;
        }

        NovoCliente novoCliente = (NovoCliente) target;
        Pais pais = entityManager.find(Pais.class, novoCliente.getPaisId());

        if (pais != null && pais.possuiEstados()) {
            Assert.state(novoCliente.getEstadoId() != null, "ID do estado não pode ser nulo");
            Optional<Estado> estadoOptional = estadoRepository.findById(novoCliente.getEstadoId());
            estadoOptional.ifPresent((estado) -> {
                if (!estado.pertenceAoPais(pais)) {
                    errors.rejectValue("estadoId", null, "Estado não pertence ao país informado");
                }});
        }
    }
}
