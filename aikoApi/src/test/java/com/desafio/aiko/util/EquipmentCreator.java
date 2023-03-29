package com.desafio.aiko.util;

import com.desafio.aiko.models.entities.Equipment;

import java.util.UUID;

public class EquipmentCreator {
    public static Equipment createEquipmentToBeSaved(){
        return Equipment.builder()
                .name("CH-1337")
                .build();
    }
    public static Equipment createValidEquipment(){
        return Equipment.builder()
                .name("CH-1337")
                .id(UUID.fromString("1d222cdc-01dd-4caa-8934-5351d3995cfb"))
                .build();
    }
    public static Equipment createValidUpdatedEquipment(){
        return Equipment.builder()
                .name("UV-0001")
                .id(UUID.fromString("1d222cdc-01dd-4caa-8934-5351d3995cfb"))
                .build();
    }

}
