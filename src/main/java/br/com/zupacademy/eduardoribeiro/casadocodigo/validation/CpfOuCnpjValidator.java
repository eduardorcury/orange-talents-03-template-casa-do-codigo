package br.com.zupacademy.eduardoribeiro.casadocodigo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, String> {

    @Override
    public void initialize(CpfOuCnpj constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<String> cpfPatterns = new ArrayList<>(
                Arrays.asList("([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})",
                        "^(?:(?!000\\.?000\\.?000-?00).)*$",
                        "^(?:(?!000\\.?000\\.?000-?00).)*$",
                        "^(?:(?!111\\.?111\\.?111-?11).)*$",
                        "^(?:(?!222\\.?222\\.?222-?22).)*$",
                        "^(?:(?!333\\.?333\\.?333-?33).)*$",
                        "^(?:(?!444\\.?444\\.?444-?44).)*$",
                        "^(?:(?!555\\.?555\\.?555-?55).)*$",
                        "^(?:(?!666\\.?666\\.?666-?66).)*$",
                        "^(?:(?!777\\.?777\\.?777-?77).)*$",
                        "^(?:(?!888\\.?888\\.?888-?88).)*$",
                        "^(?:(?!999\\.?999\\.?999-?99).)*$")
        );

        boolean cpfValido = cpfPatterns.stream().allMatch(pattern -> Pattern.matches(pattern, value));

        String cnpjPattern = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})";
        boolean cnpjValido = Pattern.matches(cnpjPattern, value);

        return (cpfValido || cnpjValido);

    }
}
