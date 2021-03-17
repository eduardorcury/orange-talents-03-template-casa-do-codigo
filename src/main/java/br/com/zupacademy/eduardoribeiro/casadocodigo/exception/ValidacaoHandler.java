package br.com.zupacademy.eduardoribeiro.casadocodigo.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidacaoHandler {

    private final MessageSource messageSource;

    public ValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDto>> erroValidacaoHandler(MethodArgumentNotValidException exception) {

        List<ErroValidacaoDto> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroValidacaoDto erro = new ErroValidacaoDto(fieldError.getField(), mensagem);
            erros.add(erro);
        });
        return ResponseEntity.badRequest().body(erros);

    }

}
