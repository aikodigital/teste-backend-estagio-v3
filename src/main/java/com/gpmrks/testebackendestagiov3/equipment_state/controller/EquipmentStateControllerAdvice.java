package com.gpmrks.testebackendestagiov3.equipment_state.controller;

import com.gpmrks.testebackendestagiov3.equipment_state.exception.CannotDeleteEquipmentStateException;
import com.gpmrks.testebackendestagiov3.equipment_state.exception.EquipmentStateNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class EquipmentStateControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EquipmentStateNotFoundException.class)
    public ProblemDetail equipmentModelNotFoundException(EquipmentStateNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment-state/not-found"));
        problemDetail.setTitle("Estado de equipamento não encontrado");
        problemDetail.setProperty("mensagem: ", ex.getMessage());
        problemDetail.setDetail("É preciso informar um ID de estado de equipamento existente");
        problemDetail.setProperty("categoria", "Estado de Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());

        return problemDetail;
    }

    @ExceptionHandler(CannotDeleteEquipmentStateException.class)
    public ProblemDetail equipmentStateCannotBeDeleted(CannotDeleteEquipmentStateException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setType(URI.create("/equipment-state/cannot-be-deleted"));
        problemDetail.setTitle("Estado de equipamento não pode ser excluído!");
        problemDetail.setDetail("É preciso modificar os estados dos equipamentos atrelados a este estado primeiro!");
        problemDetail.setProperty("mensagem: ", "Estado de equipamento com este ID contém Equipamentos cadastrados, portanto estado não pode ser excluído!");
        problemDetail.setProperty("categoria", "Estado de Equipamento");
        problemDetail.setProperty("timeStamp", Instant.now());
        problemDetail.setProperty("stacktrace", ex.getStackTrace());
        return problemDetail;
    }
}
