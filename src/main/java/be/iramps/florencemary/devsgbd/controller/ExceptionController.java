package be.iramps.florencemary.devsgbd.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

/*
Source tutoriel : https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
 */

/**
 * Controleur pour la gestion d'erreurs liees à l'API REST (url et parametres d'url)
 * Fait partie de la boite noire de Spring Boot
 * Intercepte les erreurs soulevees par les methodes annotees par {@literal @}...Mapping
 * a des fins de developpement (pour le consommateur de l'API) afin de communiquer un message d'erreur documente
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Gère les exceptions liées aux erreurs d'url de l'API REST
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public final ResponseEntity handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof MethodArgumentTypeMismatchException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            MethodArgumentTypeMismatchException except = (MethodArgumentTypeMismatchException) ex;
            return itemNotFoundException(except, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    /** Interception d'une Exception MethodArgumentTypeMismatchException (mauvais type de parametre passe dans l'url) */
    protected ResponseEntity itemNotFoundException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, headers, status, request);
    }

    /** Interception d'une Exception non définie */
    protected ResponseEntity handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(headers, status);
    }
}
