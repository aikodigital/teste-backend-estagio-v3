package com.gpmrks.testebackendestagiov3.equipment_model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteEquipmentModelException extends RuntimeException{
    public CannotDeleteEquipmentModelException(UUID id) {
        super("Modelo de equipamento com ID " + id + " contém Equipamentos cadastrados, portanto modelo não pode ser excluído!");
    }
}
