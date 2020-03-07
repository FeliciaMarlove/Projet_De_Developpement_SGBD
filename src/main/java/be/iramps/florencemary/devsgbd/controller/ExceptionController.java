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

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    /** Gère les erreurs liées aux erreurs d'url (REST API) */
    public final ResponseEntity handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof MethodArgumentTypeMismatchException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            MethodArgumentTypeMismatchException except = (MethodArgumentTypeMismatchException) ex;
            return handleUserNotFoundException(except, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, headers, status, request);
        }
    }

    /** Réponse customisée en cas d'Exception MethodArgumentTypeMismatchException (mauvais type de paramètre passé dans l'url) */
    protected ResponseEntity handleUserNotFoundException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, headers, status, request);
    }

    /** Réponse customisée pour tous les types d'erreurs. */
    protected ResponseEntity handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(headers, status);
    }
}
