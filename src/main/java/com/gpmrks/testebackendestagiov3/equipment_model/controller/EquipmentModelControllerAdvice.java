package com.gpmrks.testebackendestagiov3.equipment_model.controller;

import com.gpmrks.testebackendestagiov3.equipment.exception.EquipmentNotFoundException;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.CannotDeleteEquipmentModelException;
import com.gpmrks.testebackendestagiov3.equipment_model.exception.EquipmentModelNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class EquipmentModelControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EquipmentModelNotFoundException.class)
    public ProblemDetail equipmentModelNotFoundException(EquipmentModelNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment-model/not-found"));
        problemDetail.setTitle("Modelo de equipamento não encontrado");
        problemDetail.setProperty("mensagem: ", ex.getMessage());
        problemDetail.setDetail("É preciso informar um ID de modelo de equipamento existente");
        problemDetail.setProperty("categoria", "Modelo de Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(CannotDeleteEquipmentModelException.class)
    public ProblemDetail equipmentModelCannotBeDeleted(CannotDeleteEquipmentModelException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment-model/cannot-be-deleted"));
        problemDetail.setTitle("Modelo de equipamento não pode ser excluído!");
        problemDetail.setDetail("É preciso excluir os equipamentos atrelados a este modelo primeiro!");
        problemDetail.setProperty("mensagem: ", "Modelo de equipamento com este ID contém Equipamentos cadastrados, portanto modelo não pode ser excluído!");
        problemDetail.setProperty("categoria", "Modelo de Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());
        return problemDetail;
    }
}
