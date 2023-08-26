package com.gpmrks.testebackendestagiov3.equipment_state.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipmentStateNotFoundException extends RuntimeException{
    public EquipmentStateNotFoundException(UUID id) {
        super("Estado de equipamento não encontrado com ID " + id);
    }
}
