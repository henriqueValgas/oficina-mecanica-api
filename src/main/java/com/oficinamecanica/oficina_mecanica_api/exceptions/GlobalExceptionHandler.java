package com.oficinamecanica.oficina_mecanica_api.exceptions;

import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ErroResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroGenerico(
        Exception ex, HttpServletRequest request
    ){
        return criaRespostaErro(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "ocorreu um erro inexperado",
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarRegistroNaoEncontradoException(
            RegistroNaoEncontradoException ex, HttpServletRequest request){

        return criaRespostaErro(
                HttpStatus.NOT_FOUND,
                "Registro não encontrado",
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroResponse> tratarRegistroDuplicadoException(
            RegistroDuplicadoException ex, HttpServletRequest request){
        return criaRespostaErro(
                HttpStatus.CONFLICT,
                "Registro duplicado",
                ex.getMessage(),
                request
        );
    }

    private ResponseEntity<ErroResponse> criaRespostaErro(
            HttpStatus status,
            String erro,
            String mensagem,
            HttpServletRequest request) {
        ErroResponse response = new ErroResponse(
                LocalDateTime.now(),
                status.value(),
                erro,
                mensagem,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(response);
    }

}
