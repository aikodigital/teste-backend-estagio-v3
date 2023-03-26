package com.gpmrks.testebackendestagiov3.equipment.controller;

import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class EquipmentControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ProblemDetail equipmentNotFoundException(EquipmentNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment/not-found"));
        problemDetail.setTitle("Equipamento não encontrado");
        problemDetail.setDetail("É preciso informar um ID de um equipamento existente");
        problemDetail.setProperty("mensagem: ", ex.getMessage());
        problemDetail.setProperty("categoria", "Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());
        return problemDetail;
    }

}
