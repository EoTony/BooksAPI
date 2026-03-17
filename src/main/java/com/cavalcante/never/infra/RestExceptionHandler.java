package com.cavalcante.never.infra;

import com.cavalcante.never.exceptions.DataBaseException;
import com.cavalcante.never.exceptions.InvalidEmailException;
import com.cavalcante.never.exceptions.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<RestMessengerHandler> resourceNotFoundException(ResourceNotFoundException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.NOT_FOUND,e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(InvalidEmailException.class)
    private ResponseEntity<RestMessengerHandler> invalidEmailException(InvalidEmailException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.CONFLICT,e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestMessengerHandler> methodArgumentNotValidException(MethodArgumentNotValidException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.BAD_REQUEST,"Entrada invalida!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<RestMessengerHandler> httpMessageNotREadableException(HttpMessageNotReadableException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.BAD_REQUEST,"Entrada invalida!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<RestMessengerHandler> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.BAD_REQUEST,"Entrada invalida!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestMessengerHandler> illegalArgumentException(IllegalArgumentException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.BAD_REQUEST,"Entrada invalida!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    private ResponseEntity<RestMessengerHandler> emptyResultDataAccessException(EmptyResultDataAccessException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.NOT_FOUND,"Objeto não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<RestMessengerHandler> dataIntegrityViolationException(DataIntegrityViolationException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.CONFLICT,"Operação não pôde ser concluída por violação de integridade dos dados.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(DataBaseException.class)
    private ResponseEntity<RestMessengerHandler> dataBaseException(DataBaseException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.CONFLICT,"Operação não pôde ser concluída por violação de integridade dos dados.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestMessengerHandler> exception(Exception e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno do sistema");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestMessengerHandler> runtimeException(RuntimeException e){
        RestMessengerHandler body = new RestMessengerHandler(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno do sistema");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }







}
