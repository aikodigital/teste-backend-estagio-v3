package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.Equipment;
import me.dri.aiko.entities.dto.EquipmentInputDTO;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {
    List<Equipment> findAll();
    UUID createEquipment(EquipmentInputDTO equipmentInputDTO);
}
