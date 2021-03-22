package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { CpfOuCnpjValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface CpfOuCnpj {

    String message() default "CPF ou CNPJ inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
