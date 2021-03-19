package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExisteEntidadeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteEntidade {

    Class<?> entidade();

    String message() default "Id = ${validatedValue} não encontrado no sistema";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
