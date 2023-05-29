package com.gpmrks.testebackendestagiov3.equipment_position_history.controller;

import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_position_history.exception.InvalidEquipmentPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class EquipmentPositionHistoryControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEquipmentPositionException.class)
    public ProblemDetail equipmentNotFoundException(InvalidEquipmentPositionException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment-position/invalid"));
        problemDetail.setTitle("Posição inválida para Equipamento");
        problemDetail.setDetail("É preciso informar uma posição válida baseada no WGS84. Latitude: de -90 graus (Polo Sul) a 90 graus (Polo Norte). Longitude: de -180 graus (180 graus Oeste) a 180 graus (180 graus Leste)");
        problemDetail.setProperty("mensagem: ", ex.getMessage());
        problemDetail.setProperty("categoria", "Posição de Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());
        return problemDetail;
    }
}
