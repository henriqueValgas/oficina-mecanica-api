package com.oficinamecanica.oficina_mecanica_api.exceptions;

import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ErroReponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroReponse> tratarErroGenerico(
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
    public ResponseEntity<ErroReponse> tratarRegistroNaoEncontradoException(
            RegistroNaoEncontradoException ex, HttpServletRequest request){

        return criaRespostaErro(
                HttpStatus.NOT_FOUND,
                "Registro não encontrado",
                ex.getMessage(),
                request
        );
    }


    private ResponseEntity<ErroReponse> criaRespostaErro(
            HttpStatus status,
            String erro,
            String mensagem,
            HttpServletRequest request) {
        ErroReponse response = new ErroReponse(
                LocalDateTime.now(),
                status.value(),
                erro,
                mensagem,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(response);
    }

}
