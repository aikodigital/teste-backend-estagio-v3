package com.gpmrks.testebackendestagiov3.equipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(UUID id) {
        super("Equipamento não encontrado com ID " + id);
    }
}
