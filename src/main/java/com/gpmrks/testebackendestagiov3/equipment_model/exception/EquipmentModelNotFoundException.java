package com.gpmrks.testebackendestagiov3.equipment_model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipmentModelNotFoundException extends RuntimeException {
    public EquipmentModelNotFoundException(UUID id) {
        super("Modelo de equipamento não encontrado com ID " + id);
    }
}
