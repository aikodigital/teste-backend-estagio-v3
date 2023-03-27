package com.gpmrks.testebackendestagiov3.equipment_position_history.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEquipmentPositionException extends RuntimeException {

    public InvalidEquipmentPositionException(float lat, float lon) {
        super("Posição inválida para equipamento! Lat: " + lat + " : Lon: " + lon);
    }
}
