package me.dri.aiko.services.interfaces;

import me.dri.aiko.entities.dto.EquipmentInputDTO;
import me.dri.aiko.entities.dto.EquipmentResponseDTO;
import me.dri.aiko.entities.dto.EquipmentUpdateDTO;

import java.util.List;

public interface EquipmentService {
    List<EquipmentResponseDTO> findAll();
    EquipmentResponseDTO findEquipmentByName(String nameEquipment);
    EquipmentResponseDTO findEquipmentById(String idEquipment);
    EquipmentResponseDTO createEquipment(EquipmentInputDTO equipmentInputDTO);
    void deleteEquipmentByName(String nameEquipment);
    void deleteEquipmentById(String idEquipment);
    EquipmentResponseDTO updateEquipmentByName(String nameEquipment, EquipmentUpdateDTO atualizedEquipment);
    EquipmentResponseDTO updateEquipmentById(String idEquipment, EquipmentUpdateDTO atualizedEquipment);

}
