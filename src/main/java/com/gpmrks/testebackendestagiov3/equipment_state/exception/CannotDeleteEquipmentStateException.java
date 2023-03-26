package com.gpmrks.testebackendestagiov3.equipment_state.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteEquipmentStateException extends RuntimeException{

    public CannotDeleteEquipmentStateException(UUID id) {
        super("Estado de equipamento com ID " + id + " contém Equipamentos cadastrados, portanto estado não pode ser excluído!");
    }
}
