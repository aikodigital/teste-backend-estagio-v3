package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentResponseDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {
    List<EquipmentResponseDTO> findAll();
    EquipmentResponseDTO findEquipmentByName(String nameEquipment);
    EquipmentResponseDTO findEquipmentById(String idEquipment);
    UUID createEquipment(EquipmentInputDTO equipmentInputDTO);
    void deleteEquipmentByName(String nameEquipment);
    void deleteEquipmentById(String idEquipment);
    UUID updateEquipmentByName(String nameEquipment, EquipmentUpdateDTO atualizedEquipment);
     UUID updateEquipmentById(String idEquipment, EquipmentUpdateDTO atualizedEquipment);

}
